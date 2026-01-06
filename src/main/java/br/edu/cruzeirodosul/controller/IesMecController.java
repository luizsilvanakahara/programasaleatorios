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

import br.edu.cruzeirodosul.business.dto.IesMecDTO;
import br.edu.cruzeirodosul.business.interfaces.IIesMec;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "iesmec", tags = "INSTITUICAO DE EDUCACAO SUPERIOR MEC")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping("/iesmec")
public class IesMecController {

	@Autowired
	private IIesMec iesMec;
	
	private static final Logger logger = Logger.getLogger(IesMecController.class);
	
	@ApiOperation(
			value = "Obtem lista de IES pelo nome", 
			notes = "Obtem lista de IES pelo nome", 
			response = IesMecDTO.class)
	@GetMapping("/nome/{nome}")
	public List<IesMecDTO> obterIesMec(@PathVariable String nome) {		
		logger.info("INÍCIO IesMecController.obterIesMec");
		return iesMec.getByNome(nome);
	}
	
	@ApiOperation(
			value = "Obtem lista de IES pelo nome", 
			notes = "Obtem lista de IES pelo nome", 
			response = IesMecDTO.class)
	@GetMapping("/{id}")
	public IesMecDTO obterIesMecPorId(@PathVariable Long id) {		
		logger.info("INÍCIO IesMecController.obterIesMecPorId");
		return iesMec.getById(id);
	}
	
	@ApiOperation(
			value = "Obtem lista de IES", 
			notes = "Obtem lista de IES", 
			response = IesMecDTO.class)
	@GetMapping()
	public List<IesMecDTO> obterTodasIesMec() {		
		logger.info("INÍCIO IesMecController.obterTodasIesMec");
		return iesMec.getAll();
	}
	
	@ApiOperation(
			value = "Salva uma IES", 
			notes = "Salva uma IES", 
			response = IesMecDTO.class)
	@PostMapping()
	public IesMecDTO salvarIesMec(@RequestBody IesMecDTO iesMecDTO) {
		logger.info("INÍCIO IesMecController.salvarIesMec");
		return iesMec.salve(iesMecDTO);
		
	}
}
