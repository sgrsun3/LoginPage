fun getBaseHeader(type: Boolean): HashMap<String, String> {
    val baseMap = HashMap<String, String>()
    if (!type) {
        baseMap["content-type"] = "application/json;charset=utf-8"
    }

    if (UserInfo.getLoginStatus()){
        baseMap["idpUserId"] = UserInfo.getIdpUserId()
        baseMap["access_token"] = UserInfo.getAccessToken()
        baseMap["buId"] = UserInfo.getIdpUserId()
    }else{
        if (!SpUtils.getBoolean(SpConstants.APP_SDK_INIT)){
            baseMap["guest"] = "guest"
        }
    }

    baseMap["scan_from"]="MYCADILLAC_APP"
    baseMap["appId"] = ConfigUtil.APP_ID
    baseMap["uuId"] = PhoneUtil.getDeviceUuid()
    baseMap["app_version"] = PhoneUtil.getVersion()
    baseMap["deviceId"] = PhoneUtil.getDeviceUuid()
    baseMap["User-Agent"] = PhoneUtil.getPhoneMessage()
    baseMap["client_id"] = ConfigUtil.CLIENT_ID
    baseMap["client_secret"] = ConfigUtil.CLIENT_SECRET
    baseMap["mobile_brand"] = PhoneUtil.getDeviceBrand()
    baseMap["mobile_model"] = PhoneUtil.getSystemModel()
    baseMap["mobile_OS"] = PhoneUtil.getSystemVersion()
    baseMap["tag"] = "android"
    return baseMap
}