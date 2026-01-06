package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.edu.cruzeirodosul.business.dto.DisciplinaDTO;
import br.edu.cruzeirodosul.business.dto.DisciplinaPadraoDTO;
import br.edu.cruzeirodosul.business.interfaces.IDisciplina;
import br.edu.cruzeirodosul.business.interfaces.IDisciplinaPadrao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "equivdisciplina", tags = "EQUIVALENCIA_DISCIPLINA")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping("/equivdisciplina")
public class EquivDiscipController {

	@Autowired
	private IDisciplina disciplinaService;

	@Autowired
	private IDisciplinaPadrao disciplinaPadraoService;
	
	private static final Logger logger = Logger.getLogger(EquivDiscipController.class);

	@GetMapping("/ies/{idIes}")
	public List<DisciplinaDTO> getDisciplinaList(@PathVariable Long idIes) {
		
		logger.info("INÍCIO EquivDiscipController.getDisciplinaList");
		
		return disciplinaService.listDisciplinaByCodEmpresa(idIes);
	}

	@GetMapping("/{idDisciplina}")
	public DisciplinaDTO getDisciplina(@PathVariable Long idDisciplina) {
		
		logger.info("INÍCIO EquivDiscipController.getDisciplina");
		
		return disciplinaService.getDisciplinaByCodDisciplina(idDisciplina);
	}

	@GetMapping("/nome/{idIes}/{nome}")
	public List<DisciplinaDTO> getDisciplinaByNome(@PathVariable Long idIes, @PathVariable String nome) {
		
		logger.info("INÍCIO EquivDiscipController.getDisciplinaByNome");
		
		return disciplinaService.listDisciplinaByCodEmpresaNomeCompleto(idIes, nome);
	}

	@GetMapping("/padrao/disciplina")
	public List<DisciplinaPadraoDTO> getDisciplinaPadraoList() {
		
		logger.info("INÍCIO EquivDiscipController.getDisciplinaPadraoList");
		
		return disciplinaPadraoService.getAll();
	}

	@GetMapping("/padrao/nome/{nome}")
	public List<DisciplinaPadraoDTO> getDisciplinaPadraoByNome(@PathVariable String nome) {
		
		logger.info("INÍCIO EquivDiscipController.getDisciplinaPadraoByNome");
		
		return disciplinaPadraoService.getByNome(nome);
	}

	@PutMapping("/associa")
	public DisciplinaDTO associaDisciplinaPadrao(@RequestBody DisciplinaDTO entidade) {
		
		logger.info("INÍCIO EquivDiscipController.associaDisciplinaPadrao");
		
		return disciplinaService.associaDisciplinaPadrao(entidade);
	}

	@PutMapping("/desassocia")
	public void desassociaDisciplinaPadrao(@RequestBody DisciplinaDTO entidade) {
		
		logger.info("INÍCIO EquivDiscipController.desassociaDisciplinaPadrao");
		
		disciplinaService.desassociaDisciplinaPadrao(entidade);
	}

	@PutMapping("/atribui")
	public DisciplinaDTO atribuDisciplinaPadrao(@RequestBody DisciplinaDTO entidade) {
		
		logger.info("INÍCIO EquivDiscipController.atribuDisciplinaPadrao");
		
		DisciplinaDTO di = disciplinaService.getDisciplinaByCodDisciplina(entidade.getIdDisciplina());
		DisciplinaPadraoDTO dp = disciplinaPadraoService.atribuiDisciplinaPadrao(di);
		entidade.setIdDisPad(dp.getId());
		return disciplinaService.associaDisciplinaPadrao(entidade);
	}
}
