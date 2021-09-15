
public interface ServiceInterface {
    // La interfaz de servicio con la que el usuario se comunica. (Patrón proxy)
    Tramite serviceRequired(Tramite unTramite, Token unToken);
}
