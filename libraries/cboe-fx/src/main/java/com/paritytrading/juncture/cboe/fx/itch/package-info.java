/**
 * This package contains an implementation of Cboe FX ITCH Protocol 1.59.
 *
 * <p>The implementation is based on the Java NIO API and consists of two
 * parts:</p>
 * <ul>
 * <li>an implementation of ITCH Session Management Protocol
 *   ({@link com.paritytrading.juncture.cboe.fx.itch.ITCHSession})</li>
 * <li>an implementation of Cboe FX Book Protocol
 *   ({@link com.paritytrading.juncture.cboe.fx.itch.CboeFXBook})</li>
 * </ul>
 *
 * <p>The implementation of ITCH Session Management Protocol consists of
 * three primary functions:</p>
 * <ul>
 * <li>data reception
 *   ({@link com.paritytrading.juncture.cboe.fx.itch.ITCHSession#receive})</li>
 * <li>data transmission</li>
 * <li>session keep-alive
 *   ({@link com.paritytrading.juncture.cboe.fx.itch.ITCHSession#keepAlive})</li>
 * </ul>
 *
 * <p>Data reception can run on one thread and data transmission and session
 * keep-alive on another without locking. Data transmission and session
 * keep-alive can run on different threads but require locking.</p>
 *
 * <p>The underlying socket channels can be either blocking or non-blocking.
 * In both cases, data transmission always blocks.</p>
 */
package com.paritytrading.juncture.cboe.fx.itch;
