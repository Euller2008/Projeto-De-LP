package AgendaAcademica;

import java.util.List;

public interface AtividadeService {
    void cadastrar(Atividades atividade);
    List<Atividades> listarTodas();
    List<Atividades> listarPendentes();
    Atividades buscarPorTitulo(String titulo) throws AtividadeNaoEncontradaException;
    void marcarComoConcluida(String titulo) throws AtividadeNaoEncontradaException;
    void remover(String titulo) throws AtividadeNaoEncontradaException;
}
