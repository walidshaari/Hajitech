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


import android.content.Context;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.mobily.api.sms.entity.R;
import com.mobily.api.sms.entity.AddSenderResponse;
import com.mobily.api.sms.entity.BalanceResponse;
import com.mobily.api.sms.entity.CheckAlphasSenderResponse;
import com.mobily.api.sms.entity.CommonResponse;

import cz.msebera.android.httpclient.Header;

/**
 * @author Mobily.ws this class describe all services
 * 
 */
public class MobilyAPI {

	private Context context;
	private String userName = "ALHARA";
	private String password = "598745";

	/**
	 * @param context
	 * @param userName
	 *            = mobily.ws username
	 * @param password
	 *            =mobily.ws password
	 */
	public MobilyAPI(Context context, String userName, String password) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.password = password;
		this.userName = userName;

	}

	/**
	 * send status api that describe if mobily.ws service is available to send
	 * sms.
	 * 
	 * @param listener
	 */
	public void sendStatus(final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.sendStatus(), new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CommonResponse sendStatus = gson.fromJson(
										responseString, CommonResponse.class);
								if (sendStatus.getResponseStatus()
										.equalsIgnoreCase("success")) {
									listener.onSuccess(sendStatus.getData());

								} else {

									listener.onFailure(sendStatus.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end sendStatus Service

	/**
	 * @param type
	 *            1= send password by sms,2 = send password by email.
	 * @param userName
	 *            =current user's userName.
	 * @param listener
	 *            = for handling response.
	 */
	public static void forgetPassword(int type, String userName,
			final OnDataReceiveListner listener, final Context context) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.forgetPassword(type, userName),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CommonResponse forgetPassword = gson.fromJson(
										responseString, CommonResponse.class);
								if (forgetPassword.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(forgetPassword.getData());

								} else {

									listener.onFailure(forgetPassword
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end forgetPassword Service

	/**
	 * change password method
	 * 
	 * @param newPassword
	 *            = new password to be change
	 * @param listener
	 */
	public void changePassword(String newPassword,
			final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.changePassword(userName, password, newPassword),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CommonResponse changePassword = gson.fromJson(
										responseString, CommonResponse.class);
								if (changePassword.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(changePassword.getData());

								} else {

									listener.onFailure(changePassword
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end changePassword Service

	/**
	 * Inquiry about your balance
	 *
     */

	public void balance(final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.balance(userName, password),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								BalanceResponse balanceResponse = gson
										.fromJson(responseString,
												BalanceResponse.class);
								if (balanceResponse.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(balanceResponse
											.getData());

								} else {

									listener.onFailure(balanceResponse
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end balance Service

	/**
	 * *please sea the documents for more info
	 * 
	 * @param sender
	 *            =sender name,title of send message
	 * @param msg
	 *            = text of message that you want to send it
	 * @param numbers
	 *            = the numbers that you want to send sms for them,the numbers
	 *            must separated by comma "," example multiple numbers=
	 *            "966555555555,966555555555,966555555555" single number
	 *            ="966555555555"
	 * @param dateSend
	 *            = must formated as month/day/year ex: 1/28/2015 note* server
	 *            date is "Asia/Riyadh" then you must convert your date to
	 *            server time zone
	 * @param timeSend
	 *            = must formated as hh:mm:ss ex: 15:50:00 note* server time is
	 *            "Asia/Riyadh" then you must convert your time to our server
	 *            time zone
	 * @param deleteKey
	 *            = this key is randomly generated by developer let delete your
	 *            later messages from server by calling delete message api and
	 *            send the deleteKey which did you send..
	 * @param alias
	 *            = this is a hint that describe from any user or website called
	 *            this method
	 * @param listener
	 */
	public void sendMessage(String sender, String msg, String numbers,
			String dateSend, String timeSend, String deleteKey, String alias,
			final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.msgSend(userName, password, sender,
							Utils.convertUnicode(msg), numbers, dateSend,
							timeSend, deleteKey, "0", "56", alias),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CommonResponse commonResponse = gson.fromJson(
										responseString, CommonResponse.class);
								if (commonResponse.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(commonResponse.getData());

								} else {

									listener.onFailure(commonResponse
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end Send message Service

	/**
	 * *please sea the documents for more info
	 * 
	 * @param sender
	 *            =sender name,title of send message
	 * @param msg
	 *            = text of message that you want to send it
	 * @param numbers
	 *            = the numbers that you want to send sms for them,the numbers
	 *            must separated by comma "," example multiple numbers=
	 *            "966555555555,966555555555,966555555555" single number
	 *            ="966555555555"
	 * @param dateSend
	 *            = must formated as month/day/year ex: 1/28/2015 note* server
	 *            date is "Asia/Riyadh" then you must convert your date to
	 *            server time zone
	 * @param timeSend
	 *            = must formated as hh:mm:ss ex: 15:50:00 note* server time is
	 *            "Asia/Riyadh" then you must convert your time to our server
	 *            time zone
	 * @param deleteKey
	 *            = this key is randomly generated by developer let delete your
	 *            later messages from server by calling delete message api and
	 *            send the deleteKey which did you send..
	 * @param alias
	 *            = this is a hint that describe from any user or website called
	 *            this method
	 * @param msgKey
	 *            = please review the documentation
	 * 
	 * 
	 * @param listener
	 */
	public void sendMessageWK(String sender, String msg, String numbers,
			String dateSend, String timeSend, String deleteKey, String alias,
			String msgKey, final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.msgSendWithKey(userName, password, sender,
							Utils.convertUnicode(msg), numbers, dateSend,
							timeSend, deleteKey, "0", "24", alias,
							Utils.convertUnicode(msgKey)),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CommonResponse commonResponse = gson.fromJson(
										responseString, CommonResponse.class);
								if (commonResponse.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(commonResponse.getData());

								} else {

									listener.onFailure(commonResponse
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end Send message Service

	/**
	 * delete specific later message by sending the deleteKey that you sent with
	 * message send Api
	 * 
	 * @param deleteKey
	 *            = this deleteKey depends on what did you send the deleteKey
	 *            when using sendMSG api
	 * @param listener
	 */
	public void deleteMessage(String deleteKey,
			final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.deleteMessage(userName, password, deleteKey),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CommonResponse commonResponse = gson.fromJson(
										responseString, CommonResponse.class);
								if (commonResponse.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(commonResponse.getData());

								} else {

									listener.onFailure(commonResponse
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}
	}// end delete message Service

	/**
	 * @param sender
	 *            =the sender number that you want to adding it.
	 * @param listener
	 */
	public void addSender(String sender, final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.addSender(userName, password, sender),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								AddSenderResponse addSenderResponse = gson
										.fromJson(responseString,
												AddSenderResponse.class);
								if (addSenderResponse.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(addSenderResponse
											.getData());

								} else {

									listener.onFailure(addSenderResponse
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

		// end delete message Service
	}

	/**
	 * this method for activate your sender name after request it
	 * 
	 * @param senderID
	 *            = returnedFrom AddSenderAPI
	 * @param activeKey
	 *            = takeFrom arrived Activation code on mobile number that
	 *            activated.
	 * @param listener
	 */
	public void activeSender(String senderID, String activeKey,
			final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.activeSender(userName, password, senderID,
							activeKey), new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CommonResponse commonResponse = gson.fromJson(
										responseString, CommonResponse.class);
								if (commonResponse.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(commonResponse.getData());

								} else {

									listener.onFailure(commonResponse
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end activeSender

	/**
	 * this method returns the status of requested sender
	 * 
	 * @param senderID
	 *            = returnedFrom AddSenderAPI
	 * @param listener
	 */
	public void checkSender(String senderID, final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.checkSender(userName, password, senderID),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CommonResponse commonResponse = gson.fromJson(
										responseString, CommonResponse.class);
								if (commonResponse.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(commonResponse.getData());

								} else {

									listener.onFailure(commonResponse
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end checkSender

	/**
	 * by using this method you are request sender name from mobily.ws support
	 * and your request will be ready after 30 ms ,support team will following
	 * your request
	 * 
	 * @param sender
	 *            = returnedFrom AddSenderAPI
	 * @param listener
	 */
	public void addAlphaSender(String sender,
			final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.addAlphaSender(userName, password, sender),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CommonResponse commonResponse = gson.fromJson(
										responseString, CommonResponse.class);
								if (commonResponse.getResponseStatus()
										.equalsIgnoreCase("Success")) {
									listener.onSuccess(commonResponse.getData());

								} else {

									listener.onFailure(commonResponse
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end addAlphaSender

	/**
	 * 
	 * this method returns status of each sender names at your account
	 * 
	 * @param listener
	 */
	public void checkAlphasSender(final OnDataReceiveListner listener) {

		RequestParams AuthenticationData = new RequestParams();
		try {

			MyConnectionType.post(context, Const.HOST, AuthenticationData,
					API_Helper.checkAlphasSender(userName, password),
					new AsyncHttpResponseHandler() {

						@Override
						public void onFailure(int arg0, Header[] arg1,
								byte[] arg2, Throwable arg3) {
							listener.onFailure(new com.mobily.api.sms.entity.Error(
									context.getString(R.string.service_not_available_ar),
									context.getString(R.string.service_not_available_en),
									0));
						}

						@Override
						public void onSuccess(int arg0, Header[] headers,
								byte[] arg2) {
							// TODO Auto-generated method stub
							try {
								String responseString = new String(arg2,
										"UTF-8");
								Gson gson = new Gson();
								CheckAlphasSenderResponse checkAlphasSenderResponse = gson
										.fromJson(responseString,
												CheckAlphasSenderResponse.class);
								if (checkAlphasSenderResponse
										.getResponseStatus().equalsIgnoreCase(
												"Success")) {
									listener.onSuccess(checkAlphasSenderResponse
											.getData());

								} else {

									listener.onFailure(checkAlphasSenderResponse
											.getError());

								}

							} catch (Exception e) {
								// TODO: handle exception
								e.printStackTrace();
								listener.onFailure(new com.mobily.api.sms.entity.Error(
										context.getString(R.string.service_not_available_ar),
										context.getString(R.string.service_not_available_en),
										0));

							}
						}

						@Override
						public void onFinish() {
							// TODO Auto-generated method stub
							super.onFinish();

						}

					});

		} catch (Exception ex) {
			listener.onFailure(new com.mobily.api.sms.entity.Error(context
					.getString(R.string.service_not_available_ar), context
					.getString(R.string.service_not_available_en), 0));

			ex.printStackTrace();
		}

	}// end checkAlphasSender

}
