package com.smart.frame.model.http.api;

/**
 * route 常量
 * @author Gjm
 * @date 2018/01/12
 */

public final class ApiServiceRoutes {
    /**
     * 首页接口 GET
     */
    public static final String INDEX = "platform/index.do";

    /**
     * 浮动型列表接口 GET
     */
    public static final String PRODUCTS_FLOAT_LIST = "gold/info/productsList.do";

    /**
     * 稳定型列表接口 GET
     */
    public static final String PRODUCTS_STEADY_LIST = "gold/info/steadyList.do";

    /**
     * 登录接口 POST
     */
    public static final String LOGIN = "loginAndRegister/login.do";

    /**
     * 注册 POST
     */
    public static final String REGISTER = "loginAndRegister/register.do";

    /**
     * 发送短信验证码 POST
     */
    public static final String SENDSMS = "info/sendSms.do";

    /**
     * 找回密码验证 GET
     */
    public static final String FIND_PWD_VERIFY = "user/pwd/findPwdVerify.do";

    /**
     * 重置登录密码 POST
     */
    public static final String RESET_LOGIN_PWD = "user/pwd/resetLoginPwd.do";

    /**
     * 用户信息 GET
     */
    public static final String GET_USER_INFO = "user/getUserInfo.do";

    /**
     * 用户计息方式初始化 GET
     */
    public static final String INTEREST_TYPE_INIT = "user/interestTypeInit.do";

    /**
     * 切换用户计息方式 POST
     */
    public static final String MODIFY_INTEREST_TYPE = "user/modifyInterestType.do";

    /**
     * 账户现金收益列表 GET
     */
    public static final String USER_CASH_LIST = "user/userCashList.do";

    /**
     * 账户黄金收益列表 GET
     */
    public static final String USER_GOLD_LIST = "user/userGoldList.do";

    /**
     * 账号黄金信息 GET
     */
    public static final String USER_GOLD_INFO = "user/userGoldInfo.do";

    /**
     * 账号活期金
     */
    public static final String USER_GOLD_CURRENT = "user/userGoldCurrentInfo.do";

    /**
     * 账号定期金
     */
    public static final String USER_GOLD_REGULAR = "user/userGoldRegularInfo.do";

    /**
     * 黄金账号明细
     */
    public static final String GOLD_DETAIL_LIST = "user/goldDetailList.do";

    /**
     * 黄金账号明细类型
     */
    public static final String GOLD_DETAIL_TYPE_LIST = "user/info/goldTradeType.do";

    /**
     * 现金账号明细 GET
     */
    public static final String CASH_DETAIL_LIST = "user/cashDetailList.do";
    /**
     * 现金账号明细类型 GET
     */
    public static final String CASH_DETAIL_TYPE_LIST = "user/info/transactionTypeList.do";

    /**
     * 账户定期金列表
     */
    public static final String USER_GOLD_REGULAR_LIST = "user/userGoldRegularList.do";

    /**
     * 活期转定期界面初始化
     */
    public static final String GOLD_TYPE_CONVERT_INIT = "gold/goldTypeConvertInit.do";

    /**
     * 活期转定期
     */
    public static final String GOLD_TYPE_CONVERT = "gold/goldTypeConvert.do";

    /**
     * 重置交易密码验证信息 GET
     */
    public static final String TRADE_PWD_VERIFY = "user/findTradePwdVerifySms.do";

    /**
     * 重置交易密码 POST
     */
    public static final String RESET_TRADE_PWD = "user/resetTradePwd.do";

    /**
     * 设置交易密码 POST
     */
    public static final String SET_TRADE_PWD = "user/setTradePwd.do";

    /**
     * 修改登录密码 POST
     */
    public static final String MODIFY_LOGIN_PWD = "user/modifyLoginPwd.do";

    /**
     * 修改交易密码 POST
     */
    public static final String MODIFY_TRADE_PWD = "user/modifyTradePwd.do";

    /**
     * 用户消息列表 GET
     */
    public static final String USER_MSG_LIST = "user/userMsgList.do";

    /**
     * 用户消息读取 POST
     */
    public static final String USER_MSG_READ = "user/userMsgRead.do";

    /**
     * 我的银行卡接口
     */
    public static final String USER_BANK_CARD = "user/userBankCard.do";

    /**
     * 银行卡解绑接口
     */
    public static final String APPLY_UNBIND_CARD = "user/applyUnbindCard.do";

    /**
     * 用户收货地址列表 GET
     */
    public static final String USER_ADDRESS_LIST = "user/userAddressList.do";

    /**
     * 添加收货地址初始化 GET
     */
    public static final String ADD_ADDRESS_INIT = "user/info/addAddressInit.do";

    /**
     * 添加收货地址 POST
     */
    public static final String ADD_ADDRESS = "user/addAddress.do";

    /**
     * 修改收货地址 POST
     */
    public static final String MODIFY_ADDRESS = "user/modifyAddress.do";

    /**
     * 删除收货地址 POST
     */
    public static final String DELETE_ADDRESS = "user/deleteAddress.do";

    /**
     * 绑卡发送短信验证码 POST
     */
    public static final String BINDS_CARD_SMS = "user/bindsCardSms.do";

    /**
     * 绑卡接口 POST
     */
    public static final String BINDS_CARD = "user/bindsCard.do";

    /**
     * 兑换码兑换
     */
    public static final String REDEEMCODE_CONVERT = "user/redeemCodeConvert.do";

    /**
     * 用户兑换码兑换记录
     */
    public static final String USER_REDEEMCODE_LIST = "user/userRedeemCodeList.do";

    /**
     * 邀请奖励初始化 GET
     */
    public static final String USER_INVITE_AWARD_INIT = "user/userInviteAwardInit.do";

    /**
     * 用户邀请奖励列表 GET
     */
    public static final String USER_INVITE_AWARD_LIST = "user/userInviteAwardList.do";

    /**
     * 用户邀请列表 GET
     */
    public static final String USER_INVITE_LIST = "user/userInviteList.do";

    /**
     * 我的优惠券列表 GET
     */
    public static final String USER_COUPONS_LIST = "user/userCouponsList.do";

    /**
     * 预约存金初始化
     */
    public static final String APPOINT_STORAGE_GOLD_INIT = "gold/appointStorageGoldInit.do";

    /**
     * 预约寸金
     */
    public static final String APPOINT_STORAGE_GOLD = "gold/appointStorageGold.do";

    /**
     * 预约存金规则
     */
    public static final String APPOINT_STORAGE_RULES = "gold/info/appointStorageRules.do";

    /**
     * 预约存金记录
     */
    public static final String APPOINTMENT_STORAGE_GOLD_LIST = "gold/appointmentStorageGoldList.do";

    /**
     * 预约提金记录
     */
    public static final String APPOINTMENT_WITHDRAWAL_GOLD_LIST = "gold/appointmentWithdrawalGoldList.do";

    /**
     * 快递提金初始化 GET
     */
    public static final String EXPRESS_WITHDRAW_GOLD = "gold/expressWithdrawGoldInit.do";

    /**
     * 快递提金验证 GET
     */
    public static final String EXPRESS_WITHDRAW_GOLD_VERIFY = "gold/expressWithdrawGoldVerify.do";

    /**
     * 提金订单支付
     */
    public static final String WITHDRAW_GOLD_ORDER = "gold/withdrawGoldOrder.do";

    /**
     * 提金线下店铺列表
     */
    public static final String STORE_LIST = "gold/info/storeList.do";

    /**
     * 门店提金初始化
     */
    public static final String STORE_WITHDRAW_GOLD = "gold/storeWithdrawGold.do";

    /**
     * 门店提金验证
     */
    public static final String STORE_WITHDRAW_VERIFY = "gold/storeWithdrawVerify.do";

    /**
     * 门店提金
     */
    public static final String STORE_WITHDRAW = "gold/storeWithdraw.do";

    /**
     * 充值初始化 POST
     */
    public static final String RECHARGE_INIT = "pay/initRechargeInfo.do";

    /**
     * 充值发送短信 POST
     */
    public static final String RECHARGE_SMS = "pay/rechargeSendSms.do";

    /**
     * 充值 POST
     */
    public static final String RECHARGE = "pay/rechargeConfirm.do";

    /**
     * 提现初始化 GET
     */
    public static final String WITHDRAW_INIT = "pay/withdrawInit.do";

    /**
     * 提现 POST
     */
    public static final String WITHDRAWAL = "pay/withdrawal.do";

    /**
     * 获取实时金价 GET
     */
    public static final String CURRENT_PRICE = "platform/currentPrice.do";

    /**
     * 金价走势 GET
     */
    public static final String PRICE_TREND = "platform/goldPriceTrend.do";

    /**
     * 浮动型产品详情 GET
     */
    public static final String PRODUCT_FLOAT_DETAIL = "gold/productDetail.do";

    /**
     * 稳健型产品详情 GET
     */
    public static final String PRODUCT_STEADY_DETAIL = "gold/solidDetail.do";

    /**
     * 稳健型产品详情（账户总额） GET
     */
    public static final String PRODUCT_STEADY_DETAIL_TRADE = "gold/solidDetailOrUser.do";

    /**
     * 购买理财产品验证 POST
     */
    public static final String BUY_PRODUCT_VERIFY = "gold/buyProductVerify.do";

    /**
     * 稳定型产品购买验证 POST
     */
    public static final String BUY_STEADY_VERIFY = "gold/buySolidProducts.do";
    /**
     * 购买理财产品订单初始化 GET
     */
    public static final String BUY_ORDER_DETAIL_INIT = "gold/buyOrderDetailInit.do";

    /**
     * 稳健型产品订单初始化 GET
     */
    public static final String BUY_ORDER_STEADY_DETAIL_INIT = "gold/buySolidOrderDetailInit.do";

    /**
     * 买金可用红包列表 GET
     */
    public static final String VALID_ENVELOPE_LIST = "user/validEnvelopeList.do";

    /**
     * 买金支付操作 POST
     */
    public static final String PAY_ORDER = "gold/payOrder.do";

    /**
     * 稳健型产品支付操作 POST
     */
    public static final String PAY_ORDER_STEADY = "gold/solidPayOrder.do";

    /**
     * 卖金初始化
     */
    public static final String SALE_GOLD_INIT = "gold/saleGoldInit.do";

    /**
     * 卖金验证
     */
    public static final String SALE_GOLD_VERIFY = "gold/saleGoldVerify.do";

    /**
     * 卖金订单详情
     */
    public static final String SALE_GOLD_ORDER_DETAIL = "gold/saleGoldOrderDetail.do";

    /**
     * 卖金操作
     */
    public static final String SALE_GOLD = "gold/saleGold.do";

    /**
     * 投资问答 GET
     */
    public static final String INVEST_QUESTION = "info/investQuestion.do";

    /**
     * 用户协议 GET
     */
    public static final String USER_PROTOCOL = "info/userProtocol.do";

    /**
     * 账户总额 GET
     */
    public static final String ACCOUNT_SUM = "user/getTUserFunds.do";

    /**
     * 网站公告 GET
     */
    public static final String SITE_NOTICE = "info/siteNotice.do";

    /**
     * 发送邮箱 POST
     */
    public static final String SEND_EMAIL = "info/sendMail.do";

    /**
     * 绑定邮箱 POST
     */
    public static final String BINDS_EMAIL = "user/pinlessEmail.do";

    /**
     * 修改手机号 验证原手机 GET
     */
    public static final String MODIFY_PHONE_VERIFY = "user/validationPhone.do";

    /**
     * 修改手机号 更好新号码 GET
     */
    public static final String MODIFY_PHONE = "user/updatePhone.do";

    /**
     * 新手专享金详情 GET
     */
    public static final String EXCLUSIVE_DETAIL = "gold/exclusiveDetailOrUser.do";

    /**
     * 新手专享金买金订单生成,验证接口 POST
     */
    public static final String EXCLUSIVE_VERIRY = "gold/buyExclusiveProducts.do";

    /**
     * 新手专享金买金订单详情 GET
     */
    public static final String EXCLUSIVE_ORDER_DETAIL = "gold/buyExclusiveOrderDetailInit.do";

    /**
     * 新手专享金订单支付 POST
     */
    public static final String EXCLUSIVE_ORDER_PAY = "gold/exclusivePayOrder.do";

    /**
     * 图片上传 POST
     */
    public static final String UPLOAD_IMG = "info/imageUpload.do";

    /**
     * 账户稳健型产品信息 GET
     */
    public static final String ACCOUNT_STEADY_INFO = "user/steadyProductInfo.do";

    /**
     * 账户稳健型产品列表 GET
     */
    public static final String ACCOUNT_STEADY_LIST = "user/steadyProductList.do";

    /**
     * 推广码
     */
    public static final String ACCOUNT_QCODE = "user/appUserInviteCode.do";

    /**
     * 移动端版本信息
     */
    public static final String APP_VERSION = "info/getAppVersionInfo.do";

    /**
     * 新手专享金
     */
    public static final String PRODUCT_SPECIAL = "gold/info/getTnoviceExclusiveGold.do";

    /**
     * 稳健型产品分类
     */
    public static final String PRODUCT_STEADY = "gold/steadyGoldList.do";

    /**
     * 浮动型产品分类
     */
    public static final String PRODUCT_FLOAT = "gold/periodicGoldList.do";

    /**
     * 获取银行卡信息 GET
     */
    public static final String GET_BANK_INFO = "info/system/dicList.do";

    /**
     * 获取发票信息
     */
    public static final String GET_INVOICE_INFO = "user/productInvoiceInfo.do";
}
