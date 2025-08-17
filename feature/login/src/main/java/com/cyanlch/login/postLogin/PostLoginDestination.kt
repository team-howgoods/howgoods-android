package com.cyanlch.login.postLogin

import com.slack.circuit.runtime.screen.Screen

interface PostLoginDestination {
    fun screen(): Screen
}
