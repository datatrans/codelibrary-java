/*
 * Zuora API Reference
 *  # Introduction  Welcome to the reference for the Zuora REST API!  <a href=\"http://en.wikipedia.org/wiki/REST_API\" target=\"_blank\">REST</a> is a web-service protocol that lends itself to rapid development by using everyday HTTP and JSON technology.  The Zuora REST API provides a broad set of operations and resources that:  * Enable Web Storefront integration between your websites. * Support self-service subscriber sign-ups and account management. * Process revenue schedules through custom revenue rule models.  ## Endpoints  The Zuora REST services are provided via the following endpoints.  | Service                 | Base URL for REST Endpoints                                                                                                                                         | |-------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------| | Production REST service | https://rest.zuora.com/v1                                                                                                                                           | | Sandbox REST service    | https://rest.apisandbox.zuora.com/v1                                                                                                                                |  The production service provides access to your live user data. The sandbox environment is a good place to test your code without affecting real-world data. To use it, you must be provisioned with a sandbox tenant - your Zuora representative can help with this if needed.  ## Accessing the API  If you have a Zuora tenant, you already have access the the API.  If you don't have a Zuora tenant, go to <a href=\"https://www.zuora.com/resource/zuora-test-drive\" target=\"_blank\">https://www.zuora.com/resource/zuora-test-drive</a> and sign up for a trial tenant. The tenant comes with seed data, such as a sample product catalog.   We recommend that you <a href=\"https://knowledgecenter.zuora.com/CF_Users_and_Administrators/A_Administrator_Settings/Manage_Users/Create_an_API_User\" target=\"_blank\">create an API user</a> specifically for making API calls. Don't log in to the Zuora UI with this account. Logging in to the UI enables a security feature that periodically expires the account's password, which may eventually cause authentication failures with the API. Note that a user role does not have write access to Zuora REST services unless it has the API Write Access permission as described in those instructions.   # Authentication  There are three ways to authenticate:  * Use an authorization cookie. The cookie authorizes the user to make calls to the REST API for the duration specified in  **Administration > Security Policies > Session timeout**. The cookie expiration time is reset with this duration after every call to the REST API. To obtain a cookie, call the REST  `connections` resource with the following API user information:     *   ID     *   password     *   entity Id or entity name (Only for [Zuora Multi-entity](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity \"Multi-entity\"). See \"Entity Id and Entity Name\" below for more information.)  *   Include the following parameters in the request header, which re-authenticates the user with each request:     *   `apiAccessKeyId`     *   `apiSecretAccessKey`     *   `entityId` or `entityName` (Only for [Zuora Multi-entity](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity \"Multi-entity\"). See \"Entity Id and Entity Name\" below for more information.) *   For CORS-enabled APIs only: Include a 'single-use' token in the request header, which re-authenticates the user with each request. See below for more details.   ## Entity Id and Entity Name  The `entityId` and `entityName`  parameters are only used for  [Zuora Multi-entity](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity).  The  `entityId` parameter specifies the Id of the entity that you want to access. The `entityName` parameter specifies the [name of the entity](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity/B_Introduction_to_Entity_and_Entity_Hierarchy#Name_and_Display_Name \"Introduction to Entity and Entity Hierarchy\") that you want to access. Note that you must have permission to access the entity. You can get the entity Id and entity name through the REST GET Entities call.  You can specify either the  `entityId` or `entityName` parameter in the authentication to access and view an entity.  *   If both `entityId` and `entityName` are specified in the authentication, an error occurs.  *   If neither  `entityId` nor  `entityName` is specified in the authentication, you will log in to the entity in which your user account is created.   See [API User Authentication](https://knowledgecenter.zuora.com/BB_Introducing_Z_Business/Multi-entity/A_Overview_of_Multi-entity#API_User_Authentication \"Zuora Multi-entity\") for more information.  ## Token Authentication for CORS-Enabled APIs  The CORS mechanism enables REST API calls to Zuora to be made directly from your customer's browser, with all credit card and security information transmitted directly to Zuora. This minimizes your PCI compliance burden, allows you to implement advanced validation on your payment forms, and makes your payment forms look just like any other part of your website.  For security reasons, instead of using cookies, an API request via CORS uses **tokens** for authentication.  The token method of authentication is only designed for use with requests that must originate from your customer's browser; **it should not be considered a replacement to the existing cookie authentication** mechanism.  See [Zuora CORS REST ](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics/G_CORS_REST \"Zuora CORS REST\")for details on how CORS works and how you can begin to implement customer calls to the Zuora REST APIs. See  [HMAC Signatures](/BC_Developers/REST_API/B_REST_API_reference/HMAC_Signatures \"HMAC Signatures\") for details on the HMAC method that returns the authentication token.    # Requests and Responses   ## Request IDs  As a general rule, when asked to supply a \"key\" for an account or subscription (accountKey, account-key, subscriptionKey, subscription-key), you can provide either the actual ID or the number of the entity.  ## HTTP Request Body  Most of the parameters and data accompanying your requests will be contained in the body of the HTTP request.  The Zuora REST API accepts JSON in the HTTP request body.  No other data format (e.g., XML) is supported.   ## Testing a Request  Use a third party client, such as Postman or Advanced REST Client, to test the Zuora REST API.  You can test the Zuora REST API from the Zuora sandbox or  production service. If connecting to the production service, bear in mind that you are working with your live production data, not sample data or test data.  ## Testing with Credit Cards  Sooner or later it will probably be necessary to test some transactions that involve credit cards. For suggestions on how to handle this, see [Going Live With Your Payment Gateway](https://knowledgecenter.zuora.com/CB_Billing/M_Payment_Gateways/C_Managing_Payment_Gateways/B_Going_Live_Payment_Gateways#Testing_with_Credit_Cards \"C_Zuora_User_Guides/A_Billing_and_Payments/M_Payment_Gateways/C_Managing_Payment_Gateways/B_Going_Live_Payment_Gateways#Testing_with_Credit_Cards\").       ## Error Handling  Responses and error codes are detailed in [Responses and errors](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics/3_Responses_and_errors \"Responses and errors\").    # Pagination  When retrieving information (using GET methods), the optional `pageSize` query parameter sets the maximum number of rows to return in a response. The maximum is `40`; larger values are treated as `40`. If this value is empty or invalid, `pageSize` typically defaults to `10`.  The default value for the maximum number of rows retrieved can be overridden at the method level.  If more rows are available, the response will include a `nextPage` element, which contains a URL for requesting the next page.  If this value is not provided, no more rows are available. No \"previous page\" element is explicitly provided; to support backward paging, use the previous call.  ## Array Size  For data items that are not paginated, the REST API supports arrays of up to 300 rows.  Thus, for instance, repeated pagination can retrieve thousands of customer accounts, but within any account an array of no more than 300 rate plans is returned.   # API Versions  The Zuora REST API is in version control. Versioning ensures that Zuora REST API changes are backward compatible. Zuora uses a major and minor version nomenclature to manage changes. By specifying a version in a REST request, you can get expected responses regardless of future changes to the API.   ## Major Version  The major version number of the REST API appears in the REST URL. Currently, Zuora only supports the **v1** major version. For example,  `POST https://rest.zuora.com/v1/subscriptions` .   ## Minor Version  Zuora uses minor versions for the REST API to control small changes. For example, a field in a REST method is deprecated and a new field is used to replace it.   Some fields in the REST methods are supported as of minor versions. If a field is not noted with a minor version, this field is available for all minor versions. If a field is noted with a minor version, this field is in version control. You must specify the supported minor version in the request header to process without an error.   If a field is in version control, it is either with a minimum minor version or a maximum minor version, or both of them. You can only use this field with the minor version between the minimum and the maximum minor versions. For example, the  `invoiceCollect` field in the POST Subscription method is in version control and its maximum minor version is 189.0. You can only use this field with the minor version 189.0 or earlier.  The supported minor versions are not serial, see [Zuora REST API Minor Version History](https://knowledgecenter.zuora.com/DC_Developers/REST_API/A_REST_basics/Zuora_REST_API_Minor_Version_History \"Zuora REST API Minor Version History\") for the fields and their supported minor versions. In our REST API documentation, if a field or feature requires a minor version number, we note that in the field description.  You only need to specify the version number when you use the fields require a minor version. To specify the minor version, set the `zuora-version` parameter to the minor version number in the request header for the request call. For example, the `collect` field is in 196.0 minor version. If you want to use this field for the POST Subscription method, set the  `zuora-version` parameter to `196.0` in the request header. The `zuora-version` parameter is case sensitive.   For all the REST API fields, by default, if the minor version is not specified in the request header, Zuora will use the minimum minor version of the REST API to avoid breaking your integration.     # Zuora Object Model The following diagram presents a high-level view of the key Zuora objects. Click the image to open it in a new tab to resize it.  <a href=\"https://www.zuora.com/wp-content/uploads/2016/10/ZuoraERD-compressor-1.jpeg\" target=\"_blank\"><img src=\"https://www.zuora.com/wp-content/uploads/2016/10/ZuoraERD-compressor-1.jpeg\" alt=\"Zuora Object Model Diagram\"></a> 
 *
 * OpenAPI spec version: 0.0.1
 * Contact: docs@zuora.com
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package io.swagger.client.model;

import java.util.Objects;
import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;


/**
 * ProxyCreateUsage
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-10-31T11:45:12.183-07:00")
public class ProxyCreateUsage   {
  @SerializedName("AccountId")
  private String accountId = null;

  @SerializedName("AccountNumber")
  private String accountNumber = null;

  @SerializedName("ChargeId")
  private String chargeId = null;

  @SerializedName("EndDateTime")
  private DateTime endDateTime = null;

  @SerializedName("Quantity")
  private Double quantity = null;

  @SerializedName("RbeStatus")
  private String rbeStatus = null;

  @SerializedName("StartDateTime")
  private DateTime startDateTime = null;

  @SerializedName("SubmissionDateTime")
  private DateTime submissionDateTime = null;

  @SerializedName("SubscriptionId")
  private String subscriptionId = null;

  @SerializedName("UOM")
  private String UOM = null;

  public ProxyCreateUsage accountId(String accountId) {
    this.accountId = accountId;
    return this;
  }

   /**
   *  The ID of the account associated with the usage data. This field is required if no value is specified for the `AccountNumber` field. **Character limit**: 32 **Values**: a valid [account ID](https://knowledgecenter.zuora.com/DC_Developers/SOAP_API/E1_SOAP_API_Object_Reference/Account) 
   * @return accountId
  **/
  @ApiModelProperty(example = "null", value = " The ID of the account associated with the usage data. This field is required if no value is specified for the `AccountNumber` field. **Character limit**: 32 **Values**: a valid [account ID](https://knowledgecenter.zuora.com/DC_Developers/SOAP_API/E1_SOAP_API_Object_Reference/Account) ")
  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public ProxyCreateUsage accountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
    return this;
  }

   /**
   *  The number of the account associated with the usage data. This field is required if no value is specified for the `AccountId` field. **Character limit**: 50 **Values**: a valid [account number](https://knowledgecenter.zuora.com/DC_Developers/SOAP_API/E1_SOAP_API_Object_Reference/Account) 
   * @return accountNumber
  **/
  @ApiModelProperty(example = "null", value = " The number of the account associated with the usage data. This field is required if no value is specified for the `AccountId` field. **Character limit**: 50 **Values**: a valid [account number](https://knowledgecenter.zuora.com/DC_Developers/SOAP_API/E1_SOAP_API_Object_Reference/Account) ")
  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public ProxyCreateUsage chargeId(String chargeId) {
    this.chargeId = chargeId;
    return this;
  }

   /**
   *  The OrginalId of the rate plan charge related to the usage record, e.g., `2c9081a03c63c94c013c6873357a0117` **Character limit**: 32 **Values**: a valid [rate plan charge OriginalID](https://knowledgecenter.zuora.com/DC_Developers/SOAP_API/E1_SOAP_API_Object_Reference/RatePlanCharge) 
   * @return chargeId
  **/
  @ApiModelProperty(example = "null", value = " The OrginalId of the rate plan charge related to the usage record, e.g., `2c9081a03c63c94c013c6873357a0117` **Character limit**: 32 **Values**: a valid [rate plan charge OriginalID](https://knowledgecenter.zuora.com/DC_Developers/SOAP_API/E1_SOAP_API_Object_Reference/RatePlanCharge) ")
  public String getChargeId() {
    return chargeId;
  }

  public void setChargeId(String chargeId) {
    this.chargeId = chargeId;
  }

  public ProxyCreateUsage endDateTime(DateTime endDateTime) {
    this.endDateTime = endDateTime;
    return this;
  }

   /**
   *  The end date and time of a range of time when usage is tracked. Use this field for reporting; this field doesn't affect usage calculation. **Character limit**: 29 **Values**: a valid [date and time value](https://knowledgecenter.zuora.com/CB_Billing/WA_Dates_in_Zuora/A_Date_and_dateTime_Format) 
   * @return endDateTime
  **/
  @ApiModelProperty(example = "null", value = " The end date and time of a range of time when usage is tracked. Use this field for reporting; this field doesn't affect usage calculation. **Character limit**: 29 **Values**: a valid [date and time value](https://knowledgecenter.zuora.com/CB_Billing/WA_Dates_in_Zuora/A_Date_and_dateTime_Format) ")
  public DateTime getEndDateTime() {
    return endDateTime;
  }

  public void setEndDateTime(DateTime endDateTime) {
    this.endDateTime = endDateTime;
  }

  public ProxyCreateUsage quantity(Double quantity) {
    this.quantity = quantity;
    return this;
  }

   /**
   *  Indicates the number of units used. **Character limit**: 16 **Values**: a valid decimal amount equal to or greater than 0 
   * @return quantity
  **/
  @ApiModelProperty(example = "null", value = " Indicates the number of units used. **Character limit**: 16 **Values**: a valid decimal amount equal to or greater than 0 ")
  public Double getQuantity() {
    return quantity;
  }

  public void setQuantity(Double quantity) {
    this.quantity = quantity;
  }

  public ProxyCreateUsage rbeStatus(String rbeStatus) {
    this.rbeStatus = rbeStatus;
    return this;
  }

   /**
   *  Indicates if the rating and billing engine (RBE) processed usage data for an invoice. **Character limit**: 9 **Values**: automatically generated to be one of the following values: `Importing`, `Pending`, `Processed` 
   * @return rbeStatus
  **/
  @ApiModelProperty(example = "null", value = " Indicates if the rating and billing engine (RBE) processed usage data for an invoice. **Character limit**: 9 **Values**: automatically generated to be one of the following values: `Importing`, `Pending`, `Processed` ")
  public String getRbeStatus() {
    return rbeStatus;
  }

  public void setRbeStatus(String rbeStatus) {
    this.rbeStatus = rbeStatus;
  }

  public ProxyCreateUsage startDateTime(DateTime startDateTime) {
    this.startDateTime = startDateTime;
    return this;
  }

   /**
   *  The start date and time of a range of time when usage is tracked. Zuora uses this field value to determine the usage date. Unlike the `EndDateTime`, the `StartDateTime` field does affect usage calculation. **Character limit**: 29 **Values**: a valid [date and time value](https://knowledgecenter.zuora.com/CB_Billing/WA_Dates_in_Zuora/A_Date_and_dateTime_Format) 
   * @return startDateTime
  **/
  @ApiModelProperty(example = "null", value = " The start date and time of a range of time when usage is tracked. Zuora uses this field value to determine the usage date. Unlike the `EndDateTime`, the `StartDateTime` field does affect usage calculation. **Character limit**: 29 **Values**: a valid [date and time value](https://knowledgecenter.zuora.com/CB_Billing/WA_Dates_in_Zuora/A_Date_and_dateTime_Format) ")
  public DateTime getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(DateTime startDateTime) {
    this.startDateTime = startDateTime;
  }

  public ProxyCreateUsage submissionDateTime(DateTime submissionDateTime) {
    this.submissionDateTime = submissionDateTime;
    return this;
  }

   /**
   *  The date when usage was submitted. **Character limit**: 29 **Values**: automatically generated 
   * @return submissionDateTime
  **/
  @ApiModelProperty(example = "null", value = " The date when usage was submitted. **Character limit**: 29 **Values**: automatically generated ")
  public DateTime getSubmissionDateTime() {
    return submissionDateTime;
  }

  public void setSubmissionDateTime(DateTime submissionDateTime) {
    this.submissionDateTime = submissionDateTime;
  }

  public ProxyCreateUsage subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

   /**
   *  The ID of the subscription that contains the fees related to the usage data. **Character limit**: 32 **Values**: a valid [subscription ID](https://knowledgecenter.zuora.com/DC_Developers/SOAP_API/E1_SOAP_API_Object_Reference/Subscription) 
   * @return subscriptionId
  **/
  @ApiModelProperty(example = "null", value = " The ID of the subscription that contains the fees related to the usage data. **Character limit**: 32 **Values**: a valid [subscription ID](https://knowledgecenter.zuora.com/DC_Developers/SOAP_API/E1_SOAP_API_Object_Reference/Subscription) ")
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public ProxyCreateUsage UOM(String UOM) {
    this.UOM = UOM;
    return this;
  }

   /**
   *  Specifies the units to measure usage. Units of measure are configured in the web-based UI. Your values depend on your configuration in **Z-Billing > Settings**. **Character limit**: **Values**: a valid unit of measure 
   * @return UOM
  **/
  @ApiModelProperty(example = "null", value = " Specifies the units to measure usage. Units of measure are configured in the web-based UI. Your values depend on your configuration in **Z-Billing > Settings**. **Character limit**: **Values**: a valid unit of measure ")
  public String getUOM() {
    return UOM;
  }

  public void setUOM(String UOM) {
    this.UOM = UOM;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProxyCreateUsage proxyCreateUsage = (ProxyCreateUsage) o;
    return Objects.equals(this.accountId, proxyCreateUsage.accountId) &&
        Objects.equals(this.accountNumber, proxyCreateUsage.accountNumber) &&
        Objects.equals(this.chargeId, proxyCreateUsage.chargeId) &&
        Objects.equals(this.endDateTime, proxyCreateUsage.endDateTime) &&
        Objects.equals(this.quantity, proxyCreateUsage.quantity) &&
        Objects.equals(this.rbeStatus, proxyCreateUsage.rbeStatus) &&
        Objects.equals(this.startDateTime, proxyCreateUsage.startDateTime) &&
        Objects.equals(this.submissionDateTime, proxyCreateUsage.submissionDateTime) &&
        Objects.equals(this.subscriptionId, proxyCreateUsage.subscriptionId) &&
        Objects.equals(this.UOM, proxyCreateUsage.UOM);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountId, accountNumber, chargeId, endDateTime, quantity, rbeStatus, startDateTime, submissionDateTime, subscriptionId, UOM);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProxyCreateUsage {\n");
    
    sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
    sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
    sb.append("    chargeId: ").append(toIndentedString(chargeId)).append("\n");
    sb.append("    endDateTime: ").append(toIndentedString(endDateTime)).append("\n");
    sb.append("    quantity: ").append(toIndentedString(quantity)).append("\n");
    sb.append("    rbeStatus: ").append(toIndentedString(rbeStatus)).append("\n");
    sb.append("    startDateTime: ").append(toIndentedString(startDateTime)).append("\n");
    sb.append("    submissionDateTime: ").append(toIndentedString(submissionDateTime)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    UOM: ").append(toIndentedString(UOM)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

