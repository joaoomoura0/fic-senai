package com.ficsenai.FIC.service.cursos;

import com.ficsenai.FIC.model.cursos.AreaAtuacao;
import com.ficsenai.FIC.model.cursos.Curso;
import com.ficsenai.FIC.model.cursos.ModalidadeEnsino;
import com.ficsenai.FIC.model.cursos.Precificacao;
import com.ficsenai.FIC.repository.AreaAtuacaoRepository;
import com.ficsenai.FIC.repository.CursoRepository;
import com.ficsenai.FIC.repository.ModalidadeEnsinoRepository;
import com.ficsenai.FIC.repository.PrecificacaoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private AreaAtuacaoRepository areaAtuacaoRepository;
    @Autowired
    private ModalidadeEnsinoRepository modalidadeEnsinoRepository;
    @Autowired
    private PrecificacaoRepository precificacaoRepository;

    @Transactional
    public Curso criarCurso(Curso curso) {
        if (!StringUtils.hasText(curso.getNomeCurso())) {
            throw new IllegalArgumentException("O nome do curso não pode ser vazio.");
        }
        if (!StringUtils.hasText(curso.getVersaoCurso())) {
            throw new IllegalArgumentException("A versão do curso não pode ser vazia.");
        }

        curso.setAreaAtuacao(areaAtuacaoRepository.findById(curso.getAreaAtuacao().getIdAreaAtuacao())
                .orElseThrow(() -> new EntityNotFoundException("Área de Atuação não encontrada.")));
        curso.setModalidadeEnsino(modalidadeEnsinoRepository.findById(curso.getModalidadeEnsino().getIdModalidadeEnsino())
                .orElseThrow(() -> new EntityNotFoundException("Modalidade de Ensino não encontrada.")));
        curso.setPrecificacao(precificacaoRepository.findById(curso.getPrecificacao().getIdPrecificacao())
                .orElseThrow(() -> new EntityNotFoundException("Precificação não encontrada.")));

        if (curso.getCargaHoraria() == null || curso.getCargaHoraria().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("A carga horária deve ser um valor positivo.");
        }
        if (curso.getValorOriginal() == null || curso.getValorOriginal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor original do curso deve ser um valor positivo.");
        }
        if (curso.getValorAtual() == null || curso.getValorAtual().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor atual do curso deve ser um valor positivo.");
        }
        if (curso.getValorAtual().compareTo(curso.getValorOriginal()) > 0) {
            throw new IllegalArgumentException("O valor atual do curso não pode ser maior que o valor original.");
        }

        return cursoRepository.save(curso);
    }

    @Transactional
    public Curso atualizarCurso(Integer idCurso, String versaoCurso, Curso cursoAtualizado) {
        Curso.CursoId cursoId = new Curso.CursoId();
        cursoId.setIdCurso(idCurso);
        cursoId.setVersaoCurso(versaoCurso);

        Curso cursoExistente = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Curso não encontrado com ID " + idCurso + " e Versão " + versaoCurso));

        if (StringUtils.hasText(cursoAtualizado.getNomeCurso())) {
            cursoExistente.setNomeCurso(cursoAtualizado.getNomeCurso());
        }
        if (StringUtils.hasText(cursoAtualizado.getVersaoCurso())) {
            cursoExistente.setVersaoCurso(cursoAtualizado.getVersaoCurso());
        }

        if (cursoAtualizado.getAreaAtuacao() != null) {
            cursoExistente.setAreaAtuacao(areaAtuacaoRepository.findById(cursoAtualizado.getAreaAtuacao().getIdAreaAtuacao())
                    .orElseThrow(() -> new EntityNotFoundException("Área de Atuação não encontrada.")));
        }
        if (cursoAtualizado.getModalidadeEnsino() != null) {
            cursoExistente.setModalidadeEnsino(modalidadeEnsinoRepository.findById(cursoAtualizado.getModalidadeEnsino().getIdModalidadeEnsino())
                    .orElseThrow(() -> new EntityNotFoundException("Modalidade de Ensino não encontrada.")));
        }
        if (cursoAtualizado.getPrecificacao() != null) {
            cursoExistente.setPrecificacao(precificacaoRepository.findById(cursoAtualizado.getPrecificacao().getIdPrecificacao())
                    .orElseThrow(() -> new EntityNotFoundException("Precificação não encontrada.")));
        }

        if (cursoAtualizado.getCargaHoraria() != null && cursoAtualizado.getCargaHoraria().compareTo(BigDecimal.ZERO) > 0) {
            cursoExistente.setCargaHoraria(cursoAtualizado.getCargaHoraria());
        }
        if (cursoAtualizado.getValorOriginal() != null && cursoAtualizado.getValorOriginal().compareTo(BigDecimal.ZERO) > 0) {
            cursoExistente.setValorOriginal(cursoAtualizado.getValorOriginal());
        }
        if (cursoAtualizado.getValorAtual() != null && cursoAtualizado.getValorAtual().compareTo(BigDecimal.ZERO) > 0) {
            if (cursoAtualizado.getValorAtual().compareTo(cursoExistente.getValorOriginal()) > 0) {
                throw new IllegalArgumentException("O valor atual não pode ser maior que o valor original.");
            }
            cursoExistente.setValorAtual(cursoAtualizado.getValorAtual());
        }

        return cursoRepository.save(cursoExistente);
    }

    public Optional<Curso> buscarCursoPorId(Integer idCurso, String versaoCurso) {
        Curso.CursoId cursoId = new Curso.CursoId();
        cursoId.setIdCurso(idCurso);
        cursoId.setVersaoCurso(versaoCurso);
        return cursoRepository.findById(cursoId);
    }

    public List<Curso> listarTodosCursos() {
        return cursoRepository.findAll();
    }
}
