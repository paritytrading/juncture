package com.paritytrading.juncture.nasdaq.itch50;

import static com.paritytrading.juncture.nasdaq.itch50.ITCH50.*;

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
    private LULDAuctionCollar         luldAuctionCollar;
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
        this.luldAuctionCollar         = new LULDAuctionCollar();
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
            systemEvent.get(buffer);
            listener.systemEvent(systemEvent);
            break;
        case MESSAGE_TYPE_STOCK_DIRECTORY:
            stockDirectory.get(buffer);
            listener.stockDirectory(stockDirectory);
            break;
        case MESSAGE_TYPE_STOCK_TRADING_ACTION:
            stockTradingAction.get(buffer);
            listener.stockTradingAction(stockTradingAction);
            break;
        case MESSAGE_TYPE_REG_SHO_RESTRICTION:
            regSHORestriction.get(buffer);
            listener.regSHORestriction(regSHORestriction);
            break;
        case MESSAGE_TYPE_MARKET_PARTICIPANT_POSITION:
            marketParticipantPosition.get(buffer);
            listener.marketParticipantPosition(marketParticipantPosition);
            break;
        case MESSAGE_TYPE_MWCB_DECLINE_LEVEL:
            mwcbDeclineLevel.get(buffer);
            listener.mwcbDeclineLevel(mwcbDeclineLevel);
            break;
        case MESSAGE_TYPE_MWCB_STATUS:
            mwcbStatus.get(buffer);
            listener.mwcbStatus(mwcbStatus);
            break;
        case MESSAGE_TYPE_IPO_QUOTING_PERIOD_UPDATE:
            ipoQuotingPeriodUpdate.get(buffer);
            listener.ipoQuotingPeriodUpdate(ipoQuotingPeriodUpdate);
            break;
        case MESSAGE_TYPE_LULD_AUCTION_COLLAR:
            luldAuctionCollar.get(buffer);
            listener.luldAuctionCollar(luldAuctionCollar);
            break;
        case MESSAGE_TYPE_ADD_ORDER:
            addOrder.get(buffer);
            listener.addOrder(addOrder);
            break;
        case MESSAGE_TYPE_ADD_ORDER_MPID:
            addOrderMPID.get(buffer);
            listener.addOrderMPID(addOrderMPID);
            break;
        case MESSAGE_TYPE_ORDER_EXECUTED:
            orderExecuted.get(buffer);
            listener.orderExecuted(orderExecuted);
            break;
        case MESSAGE_TYPE_ORDER_EXECUTED_WITH_PRICE:
            orderExecutedWithPrice.get(buffer);
            listener.orderExecutedWithPrice(orderExecutedWithPrice);
            break;
        case MESSAGE_TYPE_ORDER_CANCEL:
            orderCancel.get(buffer);
            listener.orderCancel(orderCancel);
            break;
        case MESSAGE_TYPE_ORDER_DELETE:
            orderDelete.get(buffer);
            listener.orderDelete(orderDelete);
            break;
        case MESSAGE_TYPE_ORDER_REPLACE:
            orderReplace.get(buffer);
            listener.orderReplace(orderReplace);
            break;
        case MESSAGE_TYPE_TRADE:
            trade.get(buffer);
            listener.trade(trade);
            break;
        case MESSAGE_TYPE_CROSS_TRADE:
            crossTrade.get(buffer);
            listener.crossTrade(crossTrade);
            break;
        case MESSAGE_TYPE_BROKEN_TRADE:
            brokenTrade.get(buffer);
            listener.brokenTrade(brokenTrade);
            break;
        case MESSAGE_TYPE_NOII:
            noii.get(buffer);
            listener.noii(noii);
            break;
        case MESSAGE_TYPE_RPII:
            rpii.get(buffer);
            listener.rpii(rpii);
            break;
        default:
            throw new ITCH50Exception("Unknown message type: " + (char)messageType);
        }
    }

}
