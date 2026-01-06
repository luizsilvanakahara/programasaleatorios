package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.edu.cruzeirodosul.business.dto.CursoDTO;
import br.edu.cruzeirodosul.business.dto.CursoPadraoDTO;
import br.edu.cruzeirodosul.business.interfaces.ICurso;
import br.edu.cruzeirodosul.business.interfaces.ICursoPadrao;
import br.edu.cruzeirodosul.business.interfaces.IEquivalenciaCurso;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "equivcurso", tags = "EQUIVALENCIA_CURSO")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping("/equivcurso")
public class EquivalenciaCursoPadraoController {

	@Autowired
	private IEquivalenciaCurso equivalenciaCursoService;
	
	@Autowired
	private ICursoPadrao cursoPadraoService;
	
	@Autowired
	private ICurso cursoService; 
	
	private static final Logger logger = Logger.getLogger(EquivalenciaCursoPadraoController.class);
	
	
	@ApiOperation(value = "Busca todas as Equivalencias entre Cursos e Curso Padrão buscando pelo ies", notes = "Busca as Equivalencias entre Cursos e Curso Padrão", response = CursoDTO.class, responseContainer = "OBJ")
	@GetMapping("/ies/{ies}")
	public List<CursoDTO> getAllByCodEmpresa(@PathVariable Long ies) {
		logger.info("Início getAllByCodEmpresa");
		return cursoService.getCursoListByCodEmpresa(ies);
	}
	
	@ApiOperation(value = "Busca todas as Equivalencias entre Cursos e Curso Padrão buscando pelo ies e desCurso ", notes = "Busca as Equivalencias entre Cursos e Curso Padrão por nome", response = CursoDTO.class, responseContainer = "OBJ")
	@GetMapping("/nome/{ies}/{nome}")
	public List<CursoDTO> getAllByCodEmpresaDescricaoCurso(@PathVariable Long ies,@PathVariable String nome) {
		logger.info("Início getAllByCodEmpresaDescricaoCurso");
		return cursoService.getCursoListByCodEmpresaDescricaoCurso(ies, nome);
	}
	
	@ApiOperation(value = "Busca a Equivalencias entre Cursos e Curso Padrão buscando pelo  id", notes = "Busca a Equivalencias entre Cursos e Curso Padrão por id ", response = CursoDTO.class, responseContainer = "OBJ")
	@GetMapping("/{id}")
	public CursoDTO getEquivalenciaCurso(@PathVariable Long id) {
		logger.info("Início getEquivalenciaCurso");
		return cursoService.getCurso(id);
	}

	@ApiOperation(value = "Faz a associação entre um curso e o curso padrão", notes = "Faz associação de um Curso", response = CursoDTO.class, responseContainer = "OBJ")
	@PutMapping("/associa")
	public CursoDTO associa(@RequestBody CursoDTO cursoDTO) {
		logger.info("Início associa");
		return equivalenciaCursoService.associaCurso(cursoDTO);
	}

	@ApiOperation(value = "Faz a desassociação entre um curso e o curso padrão", notes = "Faz desassociação de um Curso", response = CursoDTO.class, responseContainer = "OBJ")
	@PutMapping("/desassocia")
	public CursoDTO desassocia(@RequestBody CursoDTO cursoDTO) {
		logger.info("Início desassocia");
		return equivalenciaCursoService.desassociaCurso(cursoDTO);
	}

	@ApiOperation(value = "Atribui um curso como curso padrão", notes = "Atribui novo Curso", response = CursoDTO.class, responseContainer = "OBJ")
	@PostMapping("/atribui")
	public CursoDTO atribui(@RequestBody CursoDTO cursoDTO) {
		logger.info("Início atribui");
		return equivalenciaCursoService.atribuiCurso(cursoDTO);
	}
	
	@ApiOperation(value = "Busca todos Cursos Padrão cadastrados", notes = "Busca as Equivalencias entre Cursos e Curso Padrão", response = CursoPadraoDTO.class, responseContainer = "OBJ")
	@GetMapping("/padrao")
	public List<CursoPadraoDTO> getCursoPadraoList() {
		logger.info("Início getCursoPadraoList");
		return cursoPadraoService.getCursoPadraoList();
	}
	
	@ApiOperation(value = "Busca curso padrão por ID", notes = "Faz a busca de um CursoPadrao", response = CursoPadraoDTO.class, responseContainer = "OBJ")
	@GetMapping("/padrao/{id}")
	public CursoPadraoDTO getCursoPadrao(@PathVariable Long id) {
		logger.info("Início getCursoPadrao");
		return cursoPadraoService.getCursoPadrao(id);
	}
	
	@ApiOperation(value = "Busca curso padrão por nome", notes = "Busca CursoPadrao por nome", response = CursoPadraoDTO.class, responseContainer = "OBJ")
	@GetMapping("/padrao/nome/{nome}")
	public List<CursoPadraoDTO> getCursosPadraoByDescricaoCurso(@PathVariable String nome) {
		logger.info("Início getCursosPadraoByDescricaoCurso");
		return cursoPadraoService.getCursoPadraoByNome(nome);
	}

}
