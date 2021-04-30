package blackjack.model

import blackjack.model.score.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class JudgeTest {
    @ParameterizedTest
    @MethodSource("gamerOpponentBetProvider")
    fun `조건에 따라 수익을 계산한다`(gamerScore: Score, opponentScore: Score, betMoney: BetMoney) {
        assertThat(Judge.calculateRevenue(gamerScore, opponentScore, BetMoney(1000))).isEqualTo(betMoney)
    }

    companion object {
        @JvmStatic
        private fun gamerOpponentBetProvider(): List<Arguments> {
            return listOf(
                Arguments {
                    arrayOf(
                        Score(15),
                        Score(13),
                        BetMoney(1000)
                    )
                },
                Arguments {
                    arrayOf(
                        Score(12),
                        Score(18),
                        BetMoney(-1000)
                    )
                },
                Arguments {
                    arrayOf(
                        Score(23),
                        Score(18),
                        BetMoney(-1000)
                    )
                },
                Arguments {
                    arrayOf(
                        Score(12),
                        Score(25),
                        BetMoney(1000)
                    )
                },
                Arguments {
                    arrayOf(
                        Score(21, true),
                        Score(21, false),
                        BetMoney(1500)
                    )
                },
                Arguments {
                    arrayOf(
                        Score(21, false),
                        Score(21, true),
                        BetMoney(-1000)
                    )
                },
                Arguments {
                    arrayOf(
                        Score(21, true),
                        Score(21, true),
                        BetMoney.ZERO
                    )
                }
            )
        }
    }
}
