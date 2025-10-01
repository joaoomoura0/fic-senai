package com.ficsenai.FIC.service.pessoas;

import com.ficsenai.FIC.model.pessoas.Aluno;
import com.ficsenai.FIC.model.repository.AlunoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    @Transactional
    public Aluno criarAluno(Aluno aluno) {
        if (!StringUtils.hasText(aluno.getNomeAluno())) {
            throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");
        }

        if (StringUtils.hasText(aluno.getCpf()) && alunoRepository.findByCpf(aluno.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com este CPF.");
        }

        if (StringUtils.hasText(aluno.getEmail()) && alunoRepository.findByEmail(aluno.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com este e-mail.");
        }

        if (aluno.getDataNascimento() != null && aluno.getDataNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser futura.");
        }

        if (aluno.getStatus() == null) {
            aluno.setStatus(Aluno.StatusPessoa.ATIVO);
        }

        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno atualizarAluno(Integer id, Aluno alunoAtualizado) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));

        if (alunoAtualizado.getCpf() != null && !alunoAtualizado.getCpf().equals(alunoExistente.getCpf())) {
            throw new IllegalArgumentException("Não é permitido alterar o CPF do aluno.");
        }

        if (StringUtils.hasText(alunoAtualizado.getEmail()) && !alunoAtualizado.getEmail().equals(alunoExistente.getEmail())) {
            if (alunoRepository.findByEmail(alunoAtualizado.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Já existe outro aluno cadastrado com este e-mail.");
            }
            alunoExistente.setEmail(alunoAtualizado.getEmail());
        }

        if (!StringUtils.hasText(alunoAtualizado.getNomeAluno())) {
            throw new IllegalArgumentException("O nome do aluno não pode ser vazio.");
        }
        alunoExistente.setNomeAluno(alunoAtualizado.getNomeAluno());

        if (alunoAtualizado.getDataNascimento() != null && alunoAtualizado.getDataNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento não pode ser futura.");
        }
        alunoExistente.setDataNascimento(alunoAtualizado.getDataNascimento());

        alunoExistente.setEndereco(alunoAtualizado.getEndereco());

        return alunoRepository.save(alunoExistente);
    }

    @Transactional
    public Aluno inativarAluno(Integer id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));

        if (alunoExistente.getStatus() == Aluno.StatusPessoa.INATIVO) {
            throw new IllegalArgumentException("O aluno já está inativo.");
        }

        alunoExistente.setStatus(Aluno.StatusPessoa.INATIVO);
        return alunoRepository.save(alunoExistente);
    }

    @Transactional
    public Aluno ativarAluno(Integer id) {
        Aluno alunoExistente = alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));

        if (alunoExistente.getStatus() == Aluno.StatusPessoa.ATIVO) {
            throw new IllegalArgumentException("O aluno já está ativo.");
        }

        alunoExistente.setStatus(Aluno.StatusPessoa.ATIVO);
        return alunoRepository.save(alunoExistente);
    }

    public Optional<Aluno> buscarAlunoPorId(Integer id) {
        return alunoRepository.findById(id);
    }

    public List<Aluno> listarTodosAlunos() {
        return alunoRepository.findAll();
    }

    public List<Aluno> listarAlunosPorStatus(Aluno.StatusPessoa status) {
        return alunoRepository.findByStatus(status);
    }
}
