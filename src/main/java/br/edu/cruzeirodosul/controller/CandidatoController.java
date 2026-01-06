package br.edu.cruzeirodosul.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.edu.cruzeirodosul.business.dto.CandidDTO;
import br.edu.cruzeirodosul.business.dto.CursoMecDTO;
import br.edu.cruzeirodosul.business.interfaces.ICandid;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "candidato", tags = "CANDIDATO CANDID")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping(value = "/candidato", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CandidatoController {
	
	@Autowired
	private ICandid candidService;
	
	private static final Logger logger = Logger.getLogger(CandidatoController.class);
	
	@ApiOperation(
			value = "Busca um Candid pelo seu id", 
			notes = "Busca um Candid pelo seu id", 
			response = CursoMecDTO.class)
	@GetMapping("/idCandid/{codigoCandidato}")
	public ResponseEntity<CandidDTO> obterCandidById(@PathVariable Long codigoCandidato) {		
		logger.info("INÍCIO CandidatoController.obterCandidById");
		return candidService.getCandidById(codigoCandidato);
	}
	
	@ApiOperation(
			value = "Busca um Candid pelo cpf", 
			notes = "Busca um Candid pelo cpf", 
			response = CursoMecDTO.class)
	@GetMapping("/cpf/{cpf}")
	public ResponseEntity<CandidDTO> obterCandidByCpf(@PathVariable Long cpf) {		
		logger.info("INÍCIO CandidatoController.obterCandidByCpf");
		return candidService.getCandidByCpf(cpf);
	}
	
	@ApiOperation(
			value = "Atualiza o Candid", 
			notes = "Atualiza o Candid", 
			response = CursoMecDTO.class)
	@PutMapping()
	public ResponseEntity<CandidDTO> atualizar(@RequestBody CandidDTO candidato) {		
		logger.info("INÍCIO CandidatoController.atualizar");
		return candidService.atualizarCandid(candidato);
	}

}
