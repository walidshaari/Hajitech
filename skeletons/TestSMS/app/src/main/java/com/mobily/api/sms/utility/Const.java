/**
 * Copyright 2010-present Mobily.ws.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mobily.api.sms.utility;

/**
 * @author mobily.ws this class describe all api's url constant
 * 
 */
public class Const {
	/**
	 * all api at this path
	 */
	public static final String HOST = "http://mobily.ws/api/json/index.php";

	public static final String SEND_STATUS_METHOD_NAME = "sendStatus";

	public static final String FORGET_PASSWORD_METHOD_NAME = "forgetPassword";

	public static final String CHANGE_PASSWORD_METHOD_NAME = "changePassword";

	public static final String MSG_SEND_METHOD_NAME = "msgSend";

	public static final String MSG_SEND_WK_METHOD_NAME = "msgSendWK";

	public static final String DELETE_MSG_METHOD_NAME = "deleteMsg";

	public static final String ADD_SENDER_METHOD_NAME = "addSender";

	public static final String ACTIVE_SENDER_METHOD_NAME = "activeSender";

	public static final String CHECK_SENDER_METHOD_NAME = "checkSender";

	public static final String ADD_ALPHA_SENDER_METHOD_NAME = "addAlphaSender";

	public static final String CHECK_ALPHAS_SENDER_METHOD_NAME = "checkAlphasSender";

	public static final String BALANCE_METHOD_NAME = "balance";

}
