package com.cyanlch.network

object Endpoints {
    // Auth
    const val NAVER_LOGIN = "/api/auth/naver/callback"
    const val KAKAO_LOGIN = "/api/auth/kakao/callback"
    const val REFRESH_TOKEN = "/api/auth/renew"

    const val SURVEY_ANIMATIONS = "/api/survey/animations"
    const val SURVEY_CHARACTERS = "/api/survey/characters"
    const val SURVEY_CHARACTERS_PARAMETER = "animationIds"
}
