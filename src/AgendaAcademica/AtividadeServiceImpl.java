package AgendaAcademica;

import java.util.ArrayList;
import java.util.List;

public class AtividadeServiceImpl implements AtividadeService {
    private final List<Atividades> atividades = new ArrayList<>();

    @Override
    public void cadastrar(Atividades atividade) {
        atividades.add(atividade);
    }

    @Override
    public List<Atividades> listarTodas() {
        return new ArrayList<>(atividades);
    }

    @Override
    public List<Atividades> listarPendentes() {
        List<Atividades> pendentes = new ArrayList<>();
        for (Atividades a : atividades) {
            if (!a.isConcluida()) {
                pendentes.add(a);
            }
        }
        return pendentes;
    }

    @Override
    public Atividades buscarPorTitulo(String titulo) throws AtividadeNaoEncontradaException {
        for (Atividades a : atividades) {
            if (a.getTitulo().equalsIgnoreCase(titulo)) {
                return a;
            }
        }
        throw new AtividadeNaoEncontradaException("Atividade não encontrada: " + titulo);
    }

    @Override
    public void marcarComoConcluida(String titulo) throws AtividadeNaoEncontradaException {
        Atividades atividade = buscarPorTitulo(titulo);
        atividade.setConcluida(true);
    }

    @Override
    public void remover(String titulo) throws AtividadeNaoEncontradaException {
        Atividades atividade = buscarPorTitulo(titulo);
        atividades.remove(atividade);
    }
}
