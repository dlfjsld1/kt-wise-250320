package com.think.global

import com.think.domain.system.controller.SystemController
import com.think.domain.wiseSaying.controller.WiseSayingController
import com.think.domain.wiseSaying.repository.WiseSayingRepository
import com.think.domain.wiseSaying.service.WiseSayingService

// 불필요한 객체를 생성하지 않고 일관되게 사용하기 위해 Singleton Scope 사용
object SingletonScope {
    val wiseSayingRepository = WiseSayingRepository()
    val wiseSayingService = WiseSayingService()
    val wiseSayingController = WiseSayingController()
    val systemController = SystemController()
}