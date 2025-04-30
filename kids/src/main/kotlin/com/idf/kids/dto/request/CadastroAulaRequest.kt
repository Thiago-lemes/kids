import com.fasterxml.jackson.annotation.JsonFormat
import com.idf.kids.entity.aula.CultosEnum
import java.time.LocalDateTime

data class CadastroAulaRequest(
    val culto: CultosEnum,
    val capacidade: Int,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    val dataAula: LocalDateTime,
)