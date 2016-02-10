package org.jvirtanen.juncture.coinbase.fix;

/**
 * Enumerations for Coinbase Exchange FIX API.
 */
public class CoinbaseFIXEnumerations {

    /**
     * Values for TimeInForce(59).
     */
    public static class TimeInForceValues {

        public static final char GoodTillCancel    = '1';
        public static final char ImmediateOrCancel = '3';
        public static final char FillOrKill        = '4';
        public static final char PostOnly          = 'P';

        private TimeInForceValues() {
        }

    }

    /**
     * Values for ExecType(150).
     */
    public static class ExecTypeValues {

        public static final char NewOrder     = '0';
        public static final char Fill         = '1';
        public static final char Done         = '3';
        public static final char Canceled     = '4';
        public static final char OrderChanged = 'D';

        private ExecTypeValues() {
        }

    }

    /**
     * Values for SelfTradePrevention(7928).
     */
    public static class SelfTradePreventionValues {

        public static final char DecrementAndCancel  = 'D';
        public static final char CancelRestingOrder  = 'O';
        public static final char CancelIncomingOrder = 'N';
        public static final char CancelBothOrders    = 'B';

        private SelfTradePreventionValues() {
        }

    }

}
