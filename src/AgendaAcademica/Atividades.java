package AgendaAcademica;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class Atividades {
    private String titulo;
    private String materia;
    private LocalDate dataEntrega;
    private boolean concluida;

    public Atividades() {
    }

    public Atividades(String titulo, String materia, LocalDate dataEntrega) {
        this.titulo = titulo;
        this.materia = materia;
        this.dataEntrega = dataEntrega;
        this.concluida = false;
    }

    public long getDiasRestantes() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dataEntrega);
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public LocalDate getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(LocalDate dataEntrega) { this.dataEntrega = dataEntrega; }

    public boolean isConcluida() { return concluida; }
    public void setConcluida(boolean concluida) { this.concluida = concluida; }

    @Override
    public String toString() {
        return "Atividade{titulo='" + titulo + "', materia='" + materia +
                "', dataEntrega=" + dataEntrega + ", concluida=" + concluida + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atividades that = (Atividades) o;
        return Objects.equals(titulo, that.titulo)
                && Objects.equals(materia, that.materia)
                && Objects.equals(dataEntrega, that.dataEntrega);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, materia, dataEntrega);
    }
}