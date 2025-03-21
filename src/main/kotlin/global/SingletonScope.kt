package com.think.global

import com.think.domain.system.controller.SystemController
import com.think.domain.wiseSaying.controller.WiseSayingController
import com.think.domain.wiseSaying.repository.WiseSayingRepository
import com.think.domain.wiseSaying.service.WiseSayingService

// 불필요한 객체를 생성하지 않고 일관되게 사용하기 위해 Singleton Scope 사용
// by lazy{}로 요청 시 불러오도록 함
object SingletonScope {
    val wiseSayingRepository by lazy { WiseSayingRepository()}
val wiseSayingService by lazy { WiseSayingService() }
    val wiseSayingController by lazy { WiseSayingController() }
    val systemController by lazy { SystemController() }
}