/*
 * Copyright 2015 Juncture authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * This package contains an implementation of Cboe FX ITCH Protocol 1.65.
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
