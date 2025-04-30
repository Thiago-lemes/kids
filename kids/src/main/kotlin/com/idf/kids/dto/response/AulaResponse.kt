import com.idf.kids.dto.response.UsuarioSimplesResponse
import com.idf.kids.entity.aula.AulaEntity
import com.idf.kids.entity.aula.CultosEnum
import com.idf.kids.entity.aula.SalasEnum
import java.time.format.DateTimeFormatter
import java.util.*

data class AulaResponse(
    val id: Long,
    val culto: CultosEnum,
    val capacidade: Int,
    val dataAula: String,
    val professor: UsuarioSimplesResponse,
    val sala: SalasEnum
) {
    companion object {
        fun fromEntity(entity: AulaEntity): AulaResponse {
            return AulaResponse(
                id = entity.id,
                culto = entity.culto,
                capacidade = entity.capacidade,
                dataAula = entity.dataAula.toString(),
                professor = UsuarioSimplesResponse.fromEntity(entity.criadoPeloProfessor),
                sala = entity.sala
            )
        }
    }
}
