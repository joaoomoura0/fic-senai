package com.ficsenai.FIC.service.pessoas;

import com.ficsenai.FIC.model.pessoas.Docente;
import com.ficsenai.FIC.model.pessoas.Fic;
import com.ficsenai.FIC.model.curso.Categoria;
import com.ficsenai.FIC.model.curso.Entidade;
import com.ficsenai.FIC.model.curso.Filial;
import com.ficsenai.FIC.model.curso.Funcao;
import com.ficsenai.FIC.repository.DocenteRepository;
import com.ficsenai.FIC.repository.FicRepository;
import com.ficsenai.FIC.repository.FuncaoRepository;
import com.ficsenai.FIC.repository.CategoriaRepository;
import com.ficsenai.FIC.repository.EntidadeRepository;
import com.ficsenai.FIC.repository.FilialRepository;
import com.ficsenai.FIC.repository.SuperiorImediatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;
    @Autowired
    private FuncaoRepository funcaoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private EntidadeRepository entidadeRepository;
    @Autowired
    private FilialRepository filialRepository;
    @Autowired
    private SuperiorImediatoRepository superiorImediatoRepository;
    @Autowired
    private FicRepository ficRepository;

    @Transactional
    public Docente criarDocente(Docente docente) {
        if (!StringUtils.hasText(docente.getNomeDocente())) {
            throw new IllegalArgumentException("O nome do docente não pode ser vazio.");
        }
        if (StringUtils.hasText(docente.getCpf()) && docenteRepository.findByCpf(docente.getCpf()).isPresent()) {
            throw new IllegalArgumentException("Já existe um docente cadastrado com este CPF.");
        }
        if (StringUtils.hasText(docente.getEmail()) && docenteRepository.findByEmail(docente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Já existe um docente cadastrado com este e-mail.");
        }

        docente.setFuncao(funcaoRepository.findById(docente.getFuncao().getIdFuncao())
                .orElseThrow(() -> new EntityNotFoundException("Função não encontrada.")));
        docente.setCategoria(categoriaRepository.findById(docente.getCategoria().getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada.")));
        docente.setEntidade(entidadeRepository.findById(docente.getEntidade().getIdEntidade())
                .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada.")));
        docente.setFilial(filialRepository.findById(docente.getFilial().getIdFilial())
                .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada.")));

        if (docente.getSuperiorImediato() != null && docente.getSuperiorImediato().getIdSuperiorImediato() != null) {
            docente.setSuperiorImediato(superiorImediatoRepository.findById(docente.getSuperiorImediato().getIdSuperiorImediato())
                    .orElseThrow(() -> new EntityNotFoundException("Superior Imediato não encontrado.")));
        } else {
            docente.setSuperiorImediato(null);
        }

        if (docente.getStatus() == null) {
            docente.setStatus(Docente.StatusPessoa.ATIVO);
        }

        return docenteRepository.save(docente);
    }

    @Transactional
    public Docente atualizarDocente(Integer id, Docente docenteAtualizado) {
        Docente docenteExistente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente não encontrado com ID: " + id));

        if (docenteAtualizado.getCpf() != null && !docenteAtualizado.getCpf().equals(docenteExistente.getCpf())) {
            throw new IllegalArgumentException("Não é permitido alterar o CPF do docente.");
        }

        if (StringUtils.hasText(docenteAtualizado.getEmail()) && !docenteAtualizado.getEmail().equals(docenteExistente.getEmail())) {
            if (docenteRepository.findByEmail(docenteAtualizado.getEmail()).isPresent()) {
                throw new IllegalArgumentException("Já existe outro docente cadastrado com este e-mail.");
            }
            docenteExistente.setEmail(docenteAtualizado.getEmail());
        }

        if (!StringUtils.hasText(docenteAtualizado.getNomeDocente())) {
            throw new IllegalArgumentException("O nome do docente não pode ser vazio.");
        }
        docenteExistente.setNomeDocente(docenteAtualizado.getNomeDocente());

        if (docenteAtualizado.getFuncao() != null && !docenteAtualizado.getFuncao().getIdFuncao().equals(docenteExistente.getFuncao().getIdFuncao())) {
            docenteExistente.setFuncao(funcaoRepository.findById(docenteAtualizado.getFuncao().getIdFuncao())
                    .orElseThrow(() -> new EntityNotFoundException("Função não encontrada.")));
        }
        if (docenteAtualizado.getCategoria() != null && !docenteAtualizado.getCategoria().getIdCategoria().equals(docenteExistente.getCategoria().getIdCategoria())) {
            docenteExistente.setCategoria(categoriaRepository.findById(docenteAtualizado.getCategoria().getIdCategoria())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria não encontrada.")));
        }
        if (docenteAtualizado.getEntidade() != null && !docenteAtualizado.getEntidade().getIdEntidade().equals(docenteExistente.getEntidade().getIdEntidade())) {
            docenteExistente.setEntidade(entidadeRepository.findById(docenteAtualizado.getEntidade().getIdEntidade())
                    .orElseThrow(() -> new EntityNotFoundException("Entidade não encontrada.")));
        }
        if (docenteAtualizado.getFilial() != null && !docenteAtualizado.getFilial().getIdFilial().equals(docenteExistente.getFilial().getIdFilial())) {
            docenteExistente.setFilial(filialRepository.findById(docenteAtualizado.getFilial().getIdFilial())
                    .orElseThrow(() -> new EntityNotFoundException("Filial não encontrada.")));
        }

        if (docenteAtualizado.getSuperiorImediato() != null && docenteAtualizado.getSuperiorImediato().getIdSuperiorImediato() != null) {
            docenteExistente.setSuperiorImediato(superiorImediatoRepository.findById(docenteAtualizado.getSuperiorImediato().getIdSuperiorImediato())
                    .orElseThrow(() -> new EntityNotFoundException("Superior Imediato não encontrado.")));
        } else {
            docenteExistente.setSuperiorImediato(null);
        }

        return docenteRepository.save(docenteExistente);
    }

    @Transactional
    public Docente inativarDocente(Integer id) {
        Docente docenteExistente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente não encontrado com ID: " + id));

        if (docenteExistente.getStatus() == Docente.StatusPessoa.INATIVO) {
            throw new IllegalArgumentException("O docente já está inativo.");
        }

        List<Fic> ficsAtivasComDocentePrincipal = ficRepository.findByDocentePrincipal_IdDocente(id)
                .stream()
                .filter(fic -> {
                    String status = fic.getStatusFic().getDescricao();
                    return "ABERTA".equals(status) || "EM_ANDAMENTO".equals(status);
                })
                .toList();

        List<Fic> ficsAtivasComDocenteSecundario = ficRepository.findByDocenteSecundario_IdDocente(id)
                .stream()
                .filter(fic -> {
                    String status = fic.getStatusFic().getDescricao();
                    return "ABERTA".equals(status) || "EM_ANDAMENTO".equals(status);
                })
                .toList();

        if (!ficsAtivasComDocentePrincipal.isEmpty() || !ficsAtivasComDocenteSecundario.isEmpty()) {
            String mensagem = "Atenção: O docente está atribuído a FICs ativas (ABERTA/EM_ANDAMENTO). " +
                    "A inativação pode impactar o andamento dessas FICs, exigindo a substituição do docente.";
            System.out.println(mensagem);
        }

        docenteExistente.setStatus(Docente.StatusPessoa.INATIVO);
        return docenteRepository.save(docenteExistente);
    }

    @Transactional
    public Docente ativarDocente(Integer id) {
        Docente docenteExistente = docenteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Docente não encontrado com ID: " + id));

        if (docenteExistente.getStatus() == Docente.StatusPessoa.ATIVO) {
            throw new IllegalArgumentException("O docente já está ativo.");
        }

        docenteExistente.setStatus(Docente.StatusPessoa.ATIVO);
        return docenteRepository.save(docenteExistente);
    }

    public Optional<Docente> buscarDocentePorId(Integer id) {
        return docenteRepository.findById(id);
    }

    public List<Docente> listarTodosDocentes() {
        return docenteRepository.findAll();
    }

    public List<Docente> listarDocentesPorStatus(Docente.StatusPessoa status) {
        return docenteRepository.findByStatus(status);
    }
}