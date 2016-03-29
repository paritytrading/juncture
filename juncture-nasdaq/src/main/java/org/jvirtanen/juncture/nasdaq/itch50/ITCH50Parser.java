package org.jvirtanen.juncture.nasdaq.itch50;

import static org.jvirtanen.juncture.nasdaq.itch50.ITCH50.*;

import com.paritytrading.nassau.MessageListener;
import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * A parser for inbound messages.
 */
public class ITCH50Parser implements MessageListener {

    private SystemEvent               systemEvent;
    private StockDirectory            stockDirectory;
    private StockTradingAction        stockTradingAction;
    private RegSHORestriction         regSHORestriction;
    private MarketParticipantPosition marketParticipantPosition;
    private MWCBDeclineLevel          mwcbDeclineLevel;
    private MWCBStatus                mwcbStatus;
    private IPOQuotingPeriodUpdate    ipoQuotingPeriodUpdate;
    private AddOrder                  addOrder;
    private AddOrderMPID              addOrderMPID;
    private OrderExecuted             orderExecuted;
    private OrderExecutedWithPrice    orderExecutedWithPrice;
    private OrderCancel               orderCancel;
    private OrderDelete               orderDelete;
    private OrderReplace              orderReplace;
    private Trade                     trade;
    private CrossTrade                crossTrade;
    private BrokenTrade               brokenTrade;
    private NOII                      noii;
    private RPII                      rpii;

    private ITCH50Listener listener;

    /**
     * Create a parser for inbound messages.
     *
     * @param listener the message listener
     */
    public ITCH50Parser(ITCH50Listener listener) {
        this.systemEvent               = new SystemEvent();
        this.stockDirectory            = new StockDirectory();
        this.stockTradingAction        = new StockTradingAction();
        this.regSHORestriction         = new RegSHORestriction();
        this.marketParticipantPosition = new MarketParticipantPosition();
        this.mwcbDeclineLevel          = new MWCBDeclineLevel();
        this.mwcbStatus                = new MWCBStatus();
        this.ipoQuotingPeriodUpdate    = new IPOQuotingPeriodUpdate();
        this.addOrder                  = new AddOrder();
        this.addOrderMPID              = new AddOrderMPID();
        this.orderExecuted             = new OrderExecuted();
        this.orderExecutedWithPrice    = new OrderExecutedWithPrice();
        this.orderCancel               = new OrderCancel();
        this.orderDelete               = new OrderDelete();
        this.orderReplace              = new OrderReplace();
        this.trade                     = new Trade();
        this.crossTrade                = new CrossTrade();
        this.brokenTrade               = new BrokenTrade();
        this.noii                      = new NOII();
        this.rpii                      = new RPII();

        this.listener = listener;
    }

    @Override
    public void message(ByteBuffer buffer) throws IOException {
        byte messageType = buffer.get();

        switch (messageType) {
        case MESSAGE_TYPE_SYSTEM_EVENT:
            systemEvent(buffer);
            break;
        case MESSAGE_TYPE_STOCK_DIRECTORY:
            stockDirectory(buffer);
            break;
        case MESSAGE_TYPE_STOCK_TRADING_ACTION:
            stockTradingAction(buffer);
            break;
        case MESSAGE_TYPE_REG_SHO_RESTRICTION:
            regSHORestriction(buffer);
            break;
        case MESSAGE_TYPE_MARKET_PARTICIPANT_POSITION:
            marketParticipantPosition(buffer);
            break;
        case MESSAGE_TYPE_MWCB_DECLINE_LEVEL:
            mwcbDeclineLevel(buffer);
            break;
        case MESSAGE_TYPE_MWCB_STATUS:
            mwcbStatus(buffer);
            break;
        case MESSAGE_TYPE_IPO_QUOTING_PERIOD_UPDATE:
            ipoQuotingPeriodUpdate(buffer);
            break;
        case MESSAGE_TYPE_ADD_ORDER:
            addOrder(buffer);
            break;
        case MESSAGE_TYPE_ADD_ORDER_MPID:
            addOrderMPID(buffer);
            break;
        case MESSAGE_TYPE_ORDER_EXECUTED:
            orderExecuted(buffer);
            break;
        case MESSAGE_TYPE_ORDER_EXECUTED_WITH_PRICE:
            orderExecutedWithPrice(buffer);
            break;
        case MESSAGE_TYPE_ORDER_CANCEL:
            orderCancel(buffer);
            break;
        case MESSAGE_TYPE_ORDER_DELETE:
            orderDelete(buffer);
            break;
        case MESSAGE_TYPE_ORDER_REPLACE:
            orderReplace(buffer);
            break;
        case MESSAGE_TYPE_TRADE:
            trade(buffer);
            break;
        case MESSAGE_TYPE_CROSS_TRADE:
            crossTrade(buffer);
            break;
        case MESSAGE_TYPE_BROKEN_TRADE:
            brokenTrade(buffer);
            break;
        case MESSAGE_TYPE_NOII:
            noii(buffer);
            break;
        case MESSAGE_TYPE_RPII:
            rpii(buffer);
            break;
        default:
            throw new ITCH50Exception("Unknown message type: " + (char)messageType);
        }
    }

    private void systemEvent(ByteBuffer buffer) throws IOException {
        systemEvent.get(buffer);

        listener.systemEvent(systemEvent);
    }

    private void stockDirectory(ByteBuffer buffer) throws IOException {
        stockDirectory.get(buffer);

        listener.stockDirectory(stockDirectory);
    }

    private void stockTradingAction(ByteBuffer buffer) throws IOException {
        stockTradingAction.get(buffer);

        listener.stockTradingAction(stockTradingAction);
    }

    private void regSHORestriction(ByteBuffer buffer) throws IOException {
        regSHORestriction.get(buffer);

        listener.regSHORestriction(regSHORestriction);
    }

    private void marketParticipantPosition(ByteBuffer buffer) throws IOException {
        marketParticipantPosition.get(buffer);

        listener.marketParticipantPosition(marketParticipantPosition);
    }

    private void mwcbDeclineLevel(ByteBuffer buffer) throws IOException {
        mwcbDeclineLevel.get(buffer);

        listener.mwcbDeclineLevel(mwcbDeclineLevel);
    }

    private void mwcbStatus(ByteBuffer buffer) throws IOException {
        mwcbStatus.get(buffer);

        listener.mwcbStatus(mwcbStatus);
    }

    private void ipoQuotingPeriodUpdate(ByteBuffer buffer) throws IOException {
        ipoQuotingPeriodUpdate.get(buffer);

        listener.ipoQuotingPeriodUpdate(ipoQuotingPeriodUpdate);
    }

    private void addOrder(ByteBuffer buffer) throws IOException {
        addOrder.get(buffer);

        listener.addOrder(addOrder);
    }

    private void addOrderMPID(ByteBuffer buffer) throws IOException {
        addOrderMPID.get(buffer);

        listener.addOrderMPID(addOrderMPID);
    }

    private void orderExecuted(ByteBuffer buffer) throws IOException {
        orderExecuted.get(buffer);

        listener.orderExecuted(orderExecuted);
    }

    private void orderExecutedWithPrice(ByteBuffer buffer) throws IOException {
        orderExecutedWithPrice.get(buffer);

        listener.orderExecutedWithPrice(orderExecutedWithPrice);
    }

    private void orderCancel(ByteBuffer buffer) throws IOException {
        orderCancel.get(buffer);

        listener.orderCancel(orderCancel);
    }

    private void orderDelete(ByteBuffer buffer) throws IOException {
        orderDelete.get(buffer);

        listener.orderDelete(orderDelete);
    }

    private void orderReplace(ByteBuffer buffer) throws IOException {
        orderReplace.get(buffer);

        listener.orderReplace(orderReplace);
    }

    private void trade(ByteBuffer buffer) throws IOException {
        trade.get(buffer);

        listener.trade(trade);
    }

    private void crossTrade(ByteBuffer buffer) throws IOException {
        crossTrade.get(buffer);

        listener.crossTrade(crossTrade);
    }

    private void brokenTrade(ByteBuffer buffer) throws IOException {
        brokenTrade.get(buffer);

        listener.brokenTrade(brokenTrade);
    }

    private void noii(ByteBuffer buffer) throws IOException {
        noii.get(buffer);

        listener.noii(noii);
    }

    private void rpii(ByteBuffer buffer) throws IOException {
        rpii.get(buffer);

        listener.rpii(rpii);
    }

}
