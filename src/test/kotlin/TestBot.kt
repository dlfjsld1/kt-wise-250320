import com.think.App
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TestBot {
    //companion object = 정적 메소드
    companion object {
        val originalIn = System.`in` //표준 입력 - 키보드
        val originalOut = System.`out` //표준 출력 - 모니터

        fun run(input: String): String {

            val formattedInput = input
                .trimIndent()
                .plus("\n종료")

            // use는 사용한 주체가 자동으로 해제되는 것을 보장함
            // use 안에서 진행되는 람다는 예외가 발생 시 use를 사용한 주체가 자동으로 해제됨
            // 물론 정상적으로 끝나도 자동으로 해제된다.
            ByteArrayOutputStream().use { out ->
                PrintStream(out).use {
                    try {
                        System.setOut(it)
                        System.setIn(formattedInput.byteInputStream()) // 커스텀 입력 - 매개변수 문자열

                        val app = App()
                        app.run()
                    } finally {
                        System.setIn(originalIn) // 표준 입력으로 되돌림
                        System.setOut(originalOut) // 표준 출력으로 되돌림
                    }
                } // 커스텀 출력 - 배열
                return out.toString().trim().replace("\\r\\n", "\n")

            } // 결과 값을 저장할 ByteArrayOutputStream 객체를 생성합니다.



        }
    }
}