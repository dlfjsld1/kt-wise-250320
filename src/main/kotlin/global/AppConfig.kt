package com.think.global

import java.nio.file.Path

object AppConfig {

    private var mode: String = "dev"

    fun setTestModde() {
        mode = "test"
    }

    fun setDevMode() {
        mode = "dev"
    }

    //기본 저장소 루트 경로를 모드에 따라 변경
    val tableDirPath: Path
        get() = Path.of("data/${mode}")
}