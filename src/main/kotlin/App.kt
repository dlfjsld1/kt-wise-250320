package com.think

import com.think.domain.system.controller.SystemController
import com.think.domain.wiseSaying.controller.WiseSayingController
import com.think.global.Request
import com.think.global.SingletonScope

class App {
    fun run() {
        val wiseSayingController = SingletonScope.wiseSayingController
        val systemController = SingletonScope.systemController

        println("== 명언 앱 ==")
        while (true) {
            print("명령) ")
            val input = readlnOrNull() ?: ""

            val rq = Request(input)

            when (rq.actionName) {
                "종료" -> {
                    systemController.exit()
                    break
                }

                "빌드" -> wiseSayingController.build()
                "등록" -> wiseSayingController.write()
                "목록" -> wiseSayingController.list(rq)
                "수정" -> wiseSayingController.modify(rq)
                "삭제" -> wiseSayingController.delete(rq)
                else -> {
                    println("알 수 없는 명령입니다.")
                }
            }
        }
    }
}