package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.edu.cruzeirodosul.business.dto.CursoMecDTO;
import br.edu.cruzeirodosul.business.interfaces.ICursoMec;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "cursomec", tags = "INSTITUICAO DE EDUCACAO SUPERIOR MEC")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping("/cursomec")
public class CursoMecController {

	@Autowired
	private ICursoMec cursoMec;
	
	private static final Logger logger = Logger.getLogger(CursoMecController.class);
	
	@ApiOperation(
			value = "Obtem lista de CursoMec pelo idIesMec e nome", 
			notes = "Obtem lista de CursoMec pelo idIesMec e nome", 
			response = CursoMecDTO.class)
	@GetMapping("/nome/{idIesMec}/{nomeCurso}")
	public List<CursoMecDTO> obterCursoMec(@PathVariable Long idIesMec, @PathVariable String nomeCurso) {		
		logger.info("INÍCIO CursoMecController.obterCursoMec");
		return cursoMec.getCursoListByIdIesMecAndName(idIesMec, nomeCurso.toUpperCase());
	}
	
	@ApiOperation(
			value = "Salva um CursoMec", 
			notes = "Salva um CursoMec", 
			response = CursoMecDTO.class)
	@PostMapping()
	public CursoMecDTO salvarCursoMec(@RequestBody CursoMecDTO cursoMecDTO) {
		logger.info("INÍCIO CursoMecController.salvarCursoMec");
		return cursoMec.salve(cursoMecDTO);
		
	}
	
	
	@ApiOperation(
			value = "Busca um CursoMec pelo seu id", 
			notes = "Busca um CursoMec pelo seu id", 
			response = CursoMecDTO.class)
	@GetMapping("/{idCursoMec}")
	public CursoMecDTO getCursoMecById(@PathVariable Long idCursoMec) {
		logger.info("INÍCIO CursoMecController.getCursoMecById");
		return cursoMec.getCursoMecById(idCursoMec);
		
	}
	
	@ApiOperation(
			value = "Busca lista de CursoMec", 
			notes = "Busca lista de CursoMec", 
			response = CursoMecDTO.class)
	@GetMapping("/")
	public List<CursoMecDTO> getCursoList() {
		logger.info("INÍCIO CursoMecController.getCursoList");
		return cursoMec.getCursoList();
		
	}
	
	@ApiOperation(
			value = "Busca lista de CursoMec pelo idIesMec", 
			notes = "Busca lista de CursoMec pelo idIesMec", 
			response = CursoMecDTO.class)
	@GetMapping("/iesmec/{idIesMec}")
	public List<CursoMecDTO> getCursoListByIesMec(@PathVariable Long idIesMec) {
		logger.info("INÍCIO CursoMecController.getCursoListByIesMec");
		return cursoMec.getCursoMecListByIesMec(idIesMec);
	}
}
