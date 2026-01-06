package br.edu.cruzeirodosul.controller;

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

import br.edu.cruzeirodosul.business.dto.AtdCaaDTO;
import br.edu.cruzeirodosul.business.dto.AtdProCaaDTO;
import br.edu.cruzeirodosul.business.dto.CaaDTO;
import br.edu.cruzeirodosul.business.dto.SubProcessoCaaDTO;
import br.edu.cruzeirodosul.business.interfaces.ICaa;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "caa", tags = "Processos CAA")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping("/analisecurri/processo/caa")
public class CaaController {
	
	@Autowired
	ICaa caa;
	
	Logger logger = Logger.getLogger(CaaController.class);
	
	@ApiOperation(value = "Busca uma CAA por codigo de instituicao", notes = "Busca uma CAA por codigo de instituicao", response = CaaDTO.class)
	@GetMapping("/empresa/{codInst}")
	public CaaDTO getCaaPorCodigoInstituicao(@PathVariable Long codInst) {
		logger.info("Inicio getCaaPorCodigoInstituicao");
		return caa.getCaaPorCodigoInstituicao(codInst);
	}
	
	@ApiOperation(value = "Busca um idEmprPro (idProcessoPorCaa)", notes = "\"Busca um idEmprPro (idProcessoPorCaa)", response = Long.class)
	@GetMapping("/sub-proc-caa/{idSubProcesso}/{idCaa}")
	public SubProcessoCaaDTO getSubProcessoPorCaa(@PathVariable Long idSubProcesso, @PathVariable Long idCaa) {
		logger.info("Inicio getSubProcessoPorCaa");
		return caa.getSubProcessoPorCaa(idSubProcesso, idCaa);
	}	
		
	@ApiOperation(value = "Insere um atendimento do CAA", notes = "Insere um atendimento do CAA", response = AtdCaaDTO.class)
	@PostMapping("/atendimento")
	public AtdCaaDTO inserirAtendimento(@RequestBody AtdCaaDTO atdCaaDTO) {
		logger.info("Inicio inserirAtendimento");
		return caa.inserirAtendimento(atdCaaDTO);
	}	
	
	@ApiOperation(value = "Insere um atendimento do Processo do CAA", notes = "Insere um atendimento do Processo do CAA", response = AtdCaaDTO.class)
	@PostMapping("/atendimento-processo")
	public AtdProCaaDTO inserirAtendimentoProcesso(@RequestBody AtdProCaaDTO atdProCaaDTO) {
		logger.info("Inicio inserirAtendimentoProcesso");
		return caa.inserirAtendimentoProcesso(atdProCaaDTO);
	}
	
}
