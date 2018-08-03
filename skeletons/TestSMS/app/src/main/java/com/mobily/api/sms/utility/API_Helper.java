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

import org.json.JSONException;
import org.json.JSONObject;

public class API_Helper {

	/**
	 * @return sendStatus JSONOBJECT
	 * @throws JSONException
	 */
	public static JSONObject sendStatus() throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.SEND_STATUS_METHOD_NAME);
		jsonData.put("Data", jsonObject);

		return jsonData;
	}

	/**
	 * @return forgetPassword JSONOBJECT
	 * @throws JSONException
	 */
	public static JSONObject forgetPassword(int type, String userName)
			throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.FORGET_PASSWORD_METHOD_NAME);
		jsonObject.put("Params", new JSONObject().put("type", type));
		jsonObject.put("Auth", new JSONObject().put("mobile", userName));
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * 
	 * @param userName
	 *            = userName or mobile number
	 * @param currentPassword
	 *            = current password
	 * @param newPassword
	 *            =the new password to be changed
	 * @return changePassword JSONOBJECT
	 * @throws JSONException
	 */
	public static JSONObject changePassword(String userName,
			String currentPassword, String newPassword) throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.CHANGE_PASSWORD_METHOD_NAME);
		jsonObject.put("Params",
				new JSONObject().put("newPassword", newPassword));
		org.json.JSONObject auth = new JSONObject();
		auth.put("mobile", userName);
		auth.put("password", currentPassword);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject balance(String userName, String password)
			throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.BALANCE_METHOD_NAME);
		org.json.JSONObject auth = new JSONObject();
		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * @param userName
	 * @param password
	 * @param sender
	 * @param msg
	 * @param numbers
	 * @param dateSend
	 * @param timeSend
	 * @param deleteKey
	 * @param msgId
	 * @param applicationType
	 * @param domainName
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject msgSend(String userName, String password,
			String sender, String msg, String numbers, String dateSend,
			String timeSend, String deleteKey, String msgId,
			String applicationType, String domainName) throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.MSG_SEND_METHOD_NAME);
		org.json.JSONObject auth = new JSONObject();

		org.json.JSONObject jsonParams = new org.json.JSONObject();
		jsonParams.put("sender", sender);
		jsonParams.put("msg", msg);
		jsonParams.put("numbers", numbers);
		jsonParams.put("dateSend", dateSend);
		jsonParams.put("timeSend", timeSend);
		jsonParams.put("deleteKey", deleteKey);
		jsonParams.put("msgId", msgId);
		jsonParams.put("applicationType", applicationType);
		jsonParams.put("domainName", domainName);
		jsonObject.put("Params", jsonParams);

		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * @param userName
	 * @param password
	 * @param sender
	 * @param msg
	 * @param numbers
	 * @param dateSend
	 * @param timeSend
	 * @param deleteKey
	 * @param msgId
	 * @param applicationType
	 * @param domainName
	 * @param msgKey
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject msgSendWithKey(String userName, String password,
			String sender, String msg, String numbers, String dateSend,
			String timeSend, String deleteKey, String msgId,
			String applicationType, String domainName, String msgKey)
			throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.MSG_SEND_WK_METHOD_NAME);
		org.json.JSONObject auth = new JSONObject();

		org.json.JSONObject jsonParams = new org.json.JSONObject();
		jsonParams.put("sender", sender);
		jsonParams.put("msg", msg);
		jsonParams.put("msgKey", msgKey);
		jsonParams.put("numbers", numbers);
		jsonParams.put("dateSend", dateSend);
		jsonParams.put("timeSend", timeSend);
		jsonParams.put("deleteKey", deleteKey);
		jsonParams.put("msgId", msgId);
		jsonParams.put("applicationType", applicationType);
		jsonParams.put("domainName", domainName);
		jsonObject.put("Params", jsonParams);

		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * @param userName
	 * @param password
	 * @param deleteKey
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject deleteMessage(String userName, String password,
			String deleteKey) throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.DELETE_MSG_METHOD_NAME);
		jsonObject.put("Params", new JSONObject().put("deleteKey", deleteKey));
		org.json.JSONObject auth = new JSONObject();
		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * @param userName
	 * @param password
	 * @param sender
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject addSender(String userName, String password,
			String sender) throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.ADD_SENDER_METHOD_NAME);
		jsonObject.put("Params", new JSONObject().put("sender", sender));
		org.json.JSONObject auth = new JSONObject();
		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * @param userName
	 * @param password
	 * @param sender
	 * @param activeKey
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject activeSender(String userName, String password,
			String sender, String activeKey) throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.ACTIVE_SENDER_METHOD_NAME);

		org.json.JSONObject jsonParams = new org.json.JSONObject();
		jsonParams.put("activeKey", activeKey);
		jsonParams.put("senderId", sender);
		jsonObject.put("Params", jsonParams);

		org.json.JSONObject auth = new JSONObject();
		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * @param userName
	 * @param password
	 * @param sender
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject checkSender(String userName, String password,
			String sender) throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.CHECK_SENDER_METHOD_NAME);
		if (sender.contains("#")) {
			sender = sender.replace("#", "");
		}
		org.json.JSONObject jsonParams = new org.json.JSONObject();
		jsonParams.put("senderId", sender);
		jsonObject.put("Params", jsonParams);

		org.json.JSONObject auth = new JSONObject();
		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * @param userName
	 * @param password
	 * @param sender
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject addAlphaSender(String userName, String password,
			String sender) throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.ADD_ALPHA_SENDER_METHOD_NAME);

		org.json.JSONObject jsonParams = new org.json.JSONObject();
		jsonParams.put("sender", sender);
		jsonObject.put("Params", jsonParams);

		org.json.JSONObject auth = new JSONObject();
		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

	/**
	 * @param userName
	 * @param password
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject checkAlphasSender(String userName, String password)
			throws JSONException {

		org.json.JSONObject jsonData = new org.json.JSONObject();
		org.json.JSONObject jsonObject = new org.json.JSONObject();
		jsonObject.put("Method", Const.CHECK_ALPHAS_SENDER_METHOD_NAME);

		org.json.JSONObject auth = new JSONObject();
		auth.put("mobile", userName);
		auth.put("password", password);
		jsonObject.put("Auth", auth);
		jsonData.put("Data", jsonObject);

		return jsonData;

	}

}
