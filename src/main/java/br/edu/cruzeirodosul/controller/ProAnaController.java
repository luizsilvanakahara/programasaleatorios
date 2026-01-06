package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.edu.cruzeirodosul.business.dto.ProAnaDTO;
import br.edu.cruzeirodosul.business.interfaces.IProAna;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "proAna", tags = "Processo Analise")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping("/analisecurri")
public class ProAnaController {
	
	@Autowired
	IProAna proAna;
	
	Logger logger = Logger.getLogger(ProAnaController.class);
	
	@ApiOperation(value = "Salva um Processo de Análise", notes = "Salva um Processo de Análise", response = ProAnaDTO.class)
	@PostMapping()
	public ProAnaDTO salvaProAna(@RequestBody ProAnaDTO proAnaDTO) {
		logger.info("Inicio salvaProAna");
		return proAna.salvaProAna(proAnaDTO);
	}	
	
	@ApiOperation(value = "Atualiza um Processo de Análise", notes = "Atualiza um Processo de Análise", response = ProAnaDTO.class)
	@PutMapping()
	public ProAnaDTO atualizaProAna(@RequestBody ProAnaDTO proAnaDTO) {
		logger.info("Inicio atualizaProAna");
		return proAna.atualizaProAna(proAnaDTO);
	}
	
	@ApiOperation(value = "Deleta um Processo de Análise", notes = "Deleta um Processo de Análise", response = ProAnaDTO.class)
	@DeleteMapping("/{id}")
	public void deletaProAna(@PathVariable Integer id) {
		logger.info("Inicio deletaProAna");
		proAna.deletaProAna(id);
	}
	
	@ApiOperation(value = "Busca um Processo de Análise", notes = "Busca um Processo de Análise", response = ProAnaDTO.class)
	@GetMapping("/{id}")
	public ProAnaDTO getProAna(@PathVariable Integer id) {
		logger.info("Inicio getProAna");
		return proAna.getProAnaInfoComplementares(id);
	}
	
	@ApiOperation(value = "Busca todos os Processo de Análise", notes = "Busca todos os Processo de Análise", response = ProAnaDTO.class)
	@GetMapping()
	public List<ProAnaDTO> getAll(){
		logger.info("Inicio getAll");
		return proAna.getAll();
	}
	
	@ApiOperation(value = "Busca um Processo de Análise por idCanIns", notes = "Busca um Processo de Análise por idCanIns", response = ProAnaDTO.class)
	@GetMapping("/candidato/inscricao/{idCanIns}")
	public ProAnaDTO getByIdCanIns(@PathVariable Integer idCanIns) {
		logger.info("Inicio getByIdCanIns");
		return proAna.getByIdCanIns(idCanIns);
	}

}
