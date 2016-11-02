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
import io.swagger.client.model.PUTSubscriptionPreviewInvoiceItemsType;
import io.swagger.client.model.PUTSubscriptionResponseTypeChargeMetrics;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;


/**
 * PUTSubscriptionResponseType
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaClientCodegen", date = "2016-10-31T11:45:12.183-07:00")
public class PUTSubscriptionResponseType   {
  @SerializedName("amount")
  private String amount = null;

  @SerializedName("amountWithoutTax")
  private String amountWithoutTax = null;

  @SerializedName("chargeMetrics")
  private PUTSubscriptionResponseTypeChargeMetrics chargeMetrics = null;

  @SerializedName("invoiceId")
  private String invoiceId = null;

  @SerializedName("invoiceItems")
  private List<PUTSubscriptionPreviewInvoiceItemsType> invoiceItems = new ArrayList<PUTSubscriptionPreviewInvoiceItemsType>();

  @SerializedName("invoiceTargetDate")
  private LocalDate invoiceTargetDate = null;

  @SerializedName("paidAmount")
  private String paidAmount = null;

  @SerializedName("paymentId")
  private String paymentId = null;

  @SerializedName("previewChargeMetricsResponse")
  private String previewChargeMetricsResponse = null;

  @SerializedName("subscriptionId")
  private String subscriptionId = null;

  @SerializedName("success")
  private Boolean success = null;

  @SerializedName("taxAmount")
  private String taxAmount = null;

  @SerializedName("totalDeltaMrr")
  private String totalDeltaMrr = null;

  @SerializedName("totalDeltaTcv")
  private String totalDeltaTcv = null;

  public PUTSubscriptionResponseType amount(String amount) {
    this.amount = amount;
    return this;
  }

   /**
   * Invoice amount. Preview mode only. 
   * @return amount
  **/
  @ApiModelProperty(example = "null", value = "Invoice amount. Preview mode only. ")
  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public PUTSubscriptionResponseType amountWithoutTax(String amountWithoutTax) {
    this.amountWithoutTax = amountWithoutTax;
    return this;
  }

   /**
   * Invoice amount minus tax. Preview mode only. 
   * @return amountWithoutTax
  **/
  @ApiModelProperty(example = "null", value = "Invoice amount minus tax. Preview mode only. ")
  public String getAmountWithoutTax() {
    return amountWithoutTax;
  }

  public void setAmountWithoutTax(String amountWithoutTax) {
    this.amountWithoutTax = amountWithoutTax;
  }

  public PUTSubscriptionResponseType chargeMetrics(PUTSubscriptionResponseTypeChargeMetrics chargeMetrics) {
    this.chargeMetrics = chargeMetrics;
    return this;
  }

   /**
   * Get chargeMetrics
   * @return chargeMetrics
  **/
  @ApiModelProperty(example = "null", value = "")
  public PUTSubscriptionResponseTypeChargeMetrics getChargeMetrics() {
    return chargeMetrics;
  }

  public void setChargeMetrics(PUTSubscriptionResponseTypeChargeMetrics chargeMetrics) {
    this.chargeMetrics = chargeMetrics;
  }

  public PUTSubscriptionResponseType invoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
    return this;
  }

   /**
   * Invoice ID, if an invoice is generated during the update. 
   * @return invoiceId
  **/
  @ApiModelProperty(example = "null", value = "Invoice ID, if an invoice is generated during the update. ")
  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }

  public PUTSubscriptionResponseType invoiceItems(List<PUTSubscriptionPreviewInvoiceItemsType> invoiceItems) {
    this.invoiceItems = invoiceItems;
    return this;
  }

  public PUTSubscriptionResponseType addInvoiceItemsItem(PUTSubscriptionPreviewInvoiceItemsType invoiceItemsItem) {
    this.invoiceItems.add(invoiceItemsItem);
    return this;
  }

   /**
   * Container for invoice items. 
   * @return invoiceItems
  **/
  @ApiModelProperty(example = "null", value = "Container for invoice items. ")
  public List<PUTSubscriptionPreviewInvoiceItemsType> getInvoiceItems() {
    return invoiceItems;
  }

  public void setInvoiceItems(List<PUTSubscriptionPreviewInvoiceItemsType> invoiceItems) {
    this.invoiceItems = invoiceItems;
  }

  public PUTSubscriptionResponseType invoiceTargetDate(LocalDate invoiceTargetDate) {
    this.invoiceTargetDate = invoiceTargetDate;
    return this;
  }

   /**
   * Date through which charges are calculated on the invoice, as yyyy-mm-dd. Preview mode only. 
   * @return invoiceTargetDate
  **/
  @ApiModelProperty(example = "null", value = "Date through which charges are calculated on the invoice, as yyyy-mm-dd. Preview mode only. ")
  public LocalDate getInvoiceTargetDate() {
    return invoiceTargetDate;
  }

  public void setInvoiceTargetDate(LocalDate invoiceTargetDate) {
    this.invoiceTargetDate = invoiceTargetDate;
  }

  public PUTSubscriptionResponseType paidAmount(String paidAmount) {
    this.paidAmount = paidAmount;
    return this;
  }

   /**
   * Payment amount, if a payment is collected 
   * @return paidAmount
  **/
  @ApiModelProperty(example = "null", value = "Payment amount, if a payment is collected ")
  public String getPaidAmount() {
    return paidAmount;
  }

  public void setPaidAmount(String paidAmount) {
    this.paidAmount = paidAmount;
  }

  public PUTSubscriptionResponseType paymentId(String paymentId) {
    this.paymentId = paymentId;
    return this;
  }

   /**
   * Payment ID, if a payment is collected. 
   * @return paymentId
  **/
  @ApiModelProperty(example = "null", value = "Payment ID, if a payment is collected. ")
  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public PUTSubscriptionResponseType previewChargeMetricsResponse(String previewChargeMetricsResponse) {
    this.previewChargeMetricsResponse = previewChargeMetricsResponse;
    return this;
  }

   /**
   * 
   * @return previewChargeMetricsResponse
  **/
  @ApiModelProperty(example = "null", value = "")
  public String getPreviewChargeMetricsResponse() {
    return previewChargeMetricsResponse;
  }

  public void setPreviewChargeMetricsResponse(String previewChargeMetricsResponse) {
    this.previewChargeMetricsResponse = previewChargeMetricsResponse;
  }

  public PUTSubscriptionResponseType subscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
    return this;
  }

   /**
   * The ID of the resulting new subscription. 
   * @return subscriptionId
  **/
  @ApiModelProperty(example = "null", value = "The ID of the resulting new subscription. ")
  public String getSubscriptionId() {
    return subscriptionId;
  }

  public void setSubscriptionId(String subscriptionId) {
    this.subscriptionId = subscriptionId;
  }

  public PUTSubscriptionResponseType success(Boolean success) {
    this.success = success;
    return this;
  }

   /**
   * Returns `true` if the request was processed successfully. 
   * @return success
  **/
  @ApiModelProperty(example = "null", value = "Returns `true` if the request was processed successfully. ")
  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public PUTSubscriptionResponseType taxAmount(String taxAmount) {
    this.taxAmount = taxAmount;
    return this;
  }

   /**
   * Tax amount on the invoice. 
   * @return taxAmount
  **/
  @ApiModelProperty(example = "null", value = "Tax amount on the invoice. ")
  public String getTaxAmount() {
    return taxAmount;
  }

  public void setTaxAmount(String taxAmount) {
    this.taxAmount = taxAmount;
  }

  public PUTSubscriptionResponseType totalDeltaMrr(String totalDeltaMrr) {
    this.totalDeltaMrr = totalDeltaMrr;
    return this;
  }

   /**
   * Change in the subscription monthly recurring revenue as a result of the update. 
   * @return totalDeltaMrr
  **/
  @ApiModelProperty(example = "null", value = "Change in the subscription monthly recurring revenue as a result of the update. ")
  public String getTotalDeltaMrr() {
    return totalDeltaMrr;
  }

  public void setTotalDeltaMrr(String totalDeltaMrr) {
    this.totalDeltaMrr = totalDeltaMrr;
  }

  public PUTSubscriptionResponseType totalDeltaTcv(String totalDeltaTcv) {
    this.totalDeltaTcv = totalDeltaTcv;
    return this;
  }

   /**
   * Change in the total contracted value of the subscription as a result of the update. 
   * @return totalDeltaTcv
  **/
  @ApiModelProperty(example = "null", value = "Change in the total contracted value of the subscription as a result of the update. ")
  public String getTotalDeltaTcv() {
    return totalDeltaTcv;
  }

  public void setTotalDeltaTcv(String totalDeltaTcv) {
    this.totalDeltaTcv = totalDeltaTcv;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PUTSubscriptionResponseType pUTSubscriptionResponseType = (PUTSubscriptionResponseType) o;
    return Objects.equals(this.amount, pUTSubscriptionResponseType.amount) &&
        Objects.equals(this.amountWithoutTax, pUTSubscriptionResponseType.amountWithoutTax) &&
        Objects.equals(this.chargeMetrics, pUTSubscriptionResponseType.chargeMetrics) &&
        Objects.equals(this.invoiceId, pUTSubscriptionResponseType.invoiceId) &&
        Objects.equals(this.invoiceItems, pUTSubscriptionResponseType.invoiceItems) &&
        Objects.equals(this.invoiceTargetDate, pUTSubscriptionResponseType.invoiceTargetDate) &&
        Objects.equals(this.paidAmount, pUTSubscriptionResponseType.paidAmount) &&
        Objects.equals(this.paymentId, pUTSubscriptionResponseType.paymentId) &&
        Objects.equals(this.previewChargeMetricsResponse, pUTSubscriptionResponseType.previewChargeMetricsResponse) &&
        Objects.equals(this.subscriptionId, pUTSubscriptionResponseType.subscriptionId) &&
        Objects.equals(this.success, pUTSubscriptionResponseType.success) &&
        Objects.equals(this.taxAmount, pUTSubscriptionResponseType.taxAmount) &&
        Objects.equals(this.totalDeltaMrr, pUTSubscriptionResponseType.totalDeltaMrr) &&
        Objects.equals(this.totalDeltaTcv, pUTSubscriptionResponseType.totalDeltaTcv);
  }

  @Override
  public int hashCode() {
    return Objects.hash(amount, amountWithoutTax, chargeMetrics, invoiceId, invoiceItems, invoiceTargetDate, paidAmount, paymentId, previewChargeMetricsResponse, subscriptionId, success, taxAmount, totalDeltaMrr, totalDeltaTcv);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PUTSubscriptionResponseType {\n");
    
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    amountWithoutTax: ").append(toIndentedString(amountWithoutTax)).append("\n");
    sb.append("    chargeMetrics: ").append(toIndentedString(chargeMetrics)).append("\n");
    sb.append("    invoiceId: ").append(toIndentedString(invoiceId)).append("\n");
    sb.append("    invoiceItems: ").append(toIndentedString(invoiceItems)).append("\n");
    sb.append("    invoiceTargetDate: ").append(toIndentedString(invoiceTargetDate)).append("\n");
    sb.append("    paidAmount: ").append(toIndentedString(paidAmount)).append("\n");
    sb.append("    paymentId: ").append(toIndentedString(paymentId)).append("\n");
    sb.append("    previewChargeMetricsResponse: ").append(toIndentedString(previewChargeMetricsResponse)).append("\n");
    sb.append("    subscriptionId: ").append(toIndentedString(subscriptionId)).append("\n");
    sb.append("    success: ").append(toIndentedString(success)).append("\n");
    sb.append("    taxAmount: ").append(toIndentedString(taxAmount)).append("\n");
    sb.append("    totalDeltaMrr: ").append(toIndentedString(totalDeltaMrr)).append("\n");
    sb.append("    totalDeltaTcv: ").append(toIndentedString(totalDeltaTcv)).append("\n");
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

