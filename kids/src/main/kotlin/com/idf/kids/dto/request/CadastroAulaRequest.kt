import com.idf.kids.entity.aula.CultosEnum
import java.time.LocalDate

data class CadastroAulaRequest(
    val culto: CultosEnum,
    val capacidade: Int,
    val dataAula: LocalDate,
)