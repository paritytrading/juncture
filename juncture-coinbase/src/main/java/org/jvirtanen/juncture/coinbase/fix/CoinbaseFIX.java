package org.jvirtanen.juncture.coinbase.fix;

import static java.nio.charset.StandardCharsets.*;
import static org.jvirtanen.juncture.coinbase.fix.CoinbaseFIXTags.*;
import static org.jvirtanen.philadelphia.fix42.FIX42MsgTypes.*;
import static org.jvirtanen.philadelphia.fix42.FIX42Tags.*;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;
import org.jvirtanen.philadelphia.FIXMessage;
import org.jvirtanen.philadelphia.FIXValue;

/**
 * Common definitions.
 */
public class CoinbaseFIX {

    private static final String ALGORITHM = "HmacSHA256";

    private static final char SOH = '\u0001';

    private CoinbaseFIX() {
    }

    /**
     * Sign a Logon message.
     *
     * @param message a Logon message
     * @param secret the secret
     * @throws IllegalStateException if one or more of the signature tags are
     *   missing
     */
    public static void sign(FIXMessage message, String secret) {
        StringBuilder presign = new StringBuilder();

        appendField(message, SendingTime, "SendingTime(52)", presign);
        presign.append(SOH);
        presign.append(Logon);
        presign.append(SOH);
        appendField(message, MsgSeqNum, "MsgSeqNum(34)", presign);
        presign.append(SOH);
        appendField(message, SenderCompID, "SenderCompID(49)", presign);
        presign.append(SOH);
        appendField(message, TargetCompID, "TargetCompID(56)", presign);
        presign.append(SOH);
        appendField(message, Password, "Password(554)", presign);

        Key key = new SecretKeySpec(Base64.decodeBase64(secret), ALGORITHM);

        try {
            Mac mac = Mac.getInstance(ALGORITHM);

            mac.init(key);
            mac.update(presign.toString().getBytes(US_ASCII));

            String sign = Base64.encodeBase64String(mac.doFinal());

            message.addField(RawData).setString(sign);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static void appendField(FIXMessage message, int tag, String fieldName, StringBuilder s) {
        FIXValue value = message.findField(tag);
        if (value == null)
            throw new IllegalStateException(fieldName + " not found");

        value.asString(s);
    }

}
