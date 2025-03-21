package com.think.domain.wiseSaying.service

import com.think.domain.wiseSaying.entity.WiseSaying

class WiseSayingService {
    var lastId: Int = 0
    val wiseSayings = mutableListOf<WiseSaying>()

    fun write(saying: String, author: String): WiseSaying {
        val id = ++lastId
        val new = WiseSaying(id, saying, author)

        wiseSayings.add(new)

        return new
    }

    fun getitems(): List<WiseSaying> {
        //불변으로 변경해 조회되도록 수정
        return wiseSayings.toList()
    }

    fun delete(wiseSaying: WiseSaying) {
        val rst = wiseSayings.remove(wiseSaying)
    }

    fun getItem(id: Int): WiseSaying? {
        return wiseSayings.find { it.id == id }
    }

    fun modify(wiseSaying: WiseSaying, saying: String, author: String): WiseSaying? {

        //id가 동일한 첫 번째 인덱스를 찾는다
        val index = wiseSayings.indexOfFirst { it.id == wiseSaying.id }

        //복사본 일부만 커스터마이징해서 만들기
        val new = wiseSaying.copy(author = author, saying = saying)
        wiseSayings[index] = new

        return new

    }
}