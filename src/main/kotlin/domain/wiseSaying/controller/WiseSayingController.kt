package com.think.domain.wiseSaying.controller

import com.think.domain.wiseSaying.entity.WiseSaying
import com.think.global.Request

class WiseSayingController(
    var lastId: Int = 0
) {

    val wiseSayings = mutableListOf<WiseSaying>()

    fun write() {
        print("명언: ")
        val saying = readlnOrNull() ?: ""
        print("작가: ")
        val author = readlnOrNull() ?: ""
        val id = ++lastId
        wiseSayings.add(WiseSaying(id, saying, author))

        println("${lastId}번 명언이 등록되었습니다.")
    }

    fun list() {
        println("번호 / 작가 / 명언")
        println("----------------------")
        wiseSayings.forEach {
            println("${it.id} / ${it.author} / ${it.saying}")
        }
    }

    fun delete(rq: Request) {

        val id = rq.getParam("id")?.toIntOrNull()

        if (id == null) {
            println("삭제할 명언의 번호를 입력해주세요.")
            return
        }

        val rst = wiseSayings.removeIf { saying -> saying.id == id }

        if(rst) {
            println("${id}번 명언을 삭제했습니다.")
        } else {
            println("${id}번 명언은 존재하지 않습니다.")
        }
    }

    fun modify(rq: Request) {
        val id = rq.getParam("id")?.toIntOrNull()

        if (id == null) {
            println("수정할 명언의 번호를 입력해주세요.")
            return
        }

        val wiseSaying = wiseSayings.find { it.id == id }

        wiseSaying?.let {
            println("명언(기존) : ${it.saying}")
            print("명언: ")
            val saying = readlnOrNull() ?: ""

            println("작가(기존) : ${it.author}")
            print("작가: ")
            val author = readlnOrNull() ?: ""

            //복사본 일부만 커스터마이징해서 만들기
            val new = it.copy(author = author, saying = saying)

            //id가 동일한 첫 번째 인덱스를 찾는다
            val index = wiseSayings.indexOfFirst { it.id == id }

            if(index == -1) {
                println("${id}번 명언은 존재하지 않습니다.")
                return
            }

            wiseSayings[index] = new
            println("${id}번 명언을 수정했습니다.")
        }

    }
}