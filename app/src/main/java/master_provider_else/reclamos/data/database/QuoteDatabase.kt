package master_provider_else.reclamos.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import master_provider_else.reclamos.data.database.dao.UserDao
import master_provider_else.reclamos.data.database.entity.APActividadEntity
import master_provider_else.reclamos.data.database.entity.CausaAveriaEntity
import master_provider_else.reclamos.data.database.entity.DaoCoordenadasEntity
import master_provider_else.reclamos.data.database.entity.EncuestaEntity
import master_provider_else.reclamos.data.database.entity.EncuestaEnviarEntity
import master_provider_else.reclamos.data.database.entity.FotoEntity
import master_provider_else.reclamos.data.database.entity.InformeOMSAPNodoEntity
import master_provider_else.reclamos.data.database.entity.LineasEntity
import master_provider_else.reclamos.data.database.entity.MapaEntity
import master_provider_else.reclamos.data.database.entity.MaterialEntity
import master_provider_else.reclamos.data.database.entity.MotivoDesestimacionOMSEntity
import master_provider_else.reclamos.data.database.entity.PreguntaEntity
import master_provider_else.reclamos.data.database.entity.ReclamoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSAPEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSAPNodoActividadEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSDesestimadoEntity
import master_provider_else.reclamos.data.database.entity.ReclamoInformeOMSEntity
import master_provider_else.reclamos.data.database.entity.SolucionAveriaEntity
import master_provider_else.reclamos.data.database.entity.SolucionInterrupcionEntity
import master_provider_else.reclamos.data.database.entity.TipoAreaIntervencionEntity
import master_provider_else.reclamos.data.database.entity.TipoDeficienciaEntity
import master_provider_else.reclamos.data.database.entity.TipoDenunciaEntity
import master_provider_else.reclamos.data.database.entity.TipoEquipoProteccionManiobraEntity
import master_provider_else.reclamos.data.database.entity.TipoInstalacionAfectadaEntity
import master_provider_else.reclamos.data.database.entity.TipoInstalacionElectricaAfectadaEntity
import master_provider_else.reclamos.data.database.entity.TipoManiobraCapacidadEntity
import master_provider_else.reclamos.data.database.entity.UserEntity
import master_provider_else.reclamos.data.database.entity.gnMaterialEntity
import master_provider_else.reclamos.data.database.entity.gnNodosEntity

@Database(
  entities = [ReclamoEntity::class,
    CausaAveriaEntity::class,
    DaoCoordenadasEntity::class,
    PreguntaEntity::class,
    FotoEntity::class,
    MapaEntity::class,
    ReclamoInformeOMSEntity::class,
    SolucionAveriaEntity::class,
    SolucionInterrupcionEntity::class,
    TipoAreaIntervencionEntity::class,
    TipoDenunciaEntity::class,
    TipoEquipoProteccionManiobraEntity::class,
    TipoInstalacionAfectadaEntity::class,
    TipoInstalacionElectricaAfectadaEntity::class,
    TipoManiobraCapacidadEntity::class,
    LineasEntity::class,
    UserEntity::class,
    EncuestaEnviarEntity::class,
    EncuestaEntity::class,
    MaterialEntity::class,
    gnMaterialEntity::class,
    TipoDeficienciaEntity::class,
    gnNodosEntity::class,
    InformeOMSAPNodoEntity::class,
    ReclamoInformeOMSAPEntity::class,
    APActividadEntity::class,
    ReclamoInformeOMSAPNodoActividadEntity::class,
    MotivoDesestimacionOMSEntity::class,
    ReclamoInformeOMSDesestimadoEntity::class
  ],
  version = 2
)
abstract class QuoteDatabase : RoomDatabase() {
  abstract fun getLogin(): UserDao
}