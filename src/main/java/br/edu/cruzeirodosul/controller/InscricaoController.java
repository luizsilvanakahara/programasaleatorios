package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.edu.cruzeirodosul.business.dto.CadpolDTO;
import br.edu.cruzeirodosul.business.dto.CandidatoDTO;
import br.edu.cruzeirodosul.business.dto.CursoComboDTO;
import br.edu.cruzeirodosul.business.dto.DocsMatricDTO;
import br.edu.cruzeirodosul.business.dto.EmpresaDTO;
import br.edu.cruzeirodosul.business.dto.Etapa2DTO;
import br.edu.cruzeirodosul.business.dto.InscricaoDTO;
import br.edu.cruzeirodosul.business.dto.PseletDTO;
import br.edu.cruzeirodosul.business.interfaces.ICandid;
import br.edu.cruzeirodosul.business.interfaces.IInscricao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "inscricao", tags = "INSCRICAO ANALISE CURRICULAR")
@ApiResponses(value = { 
		@ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") 
		})
@RequestMapping(value = "/inscricao", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class InscricaoController {
	
	@Autowired
	private IInscricao inscricaoService;
	
	@Autowired
	private ICandid candidService;
	
	private static final Logger logger = Logger.getLogger(InscricaoController.class);
	
	@ApiOperation(
			value = "Obtem candidato por cpf, codvest e seq", 
			notes = "Obtem candidato por cpf, codvest e seq", response = CandidatoDTO.class)
	@GetMapping("/candidato/{cpf}/{codVest}/{seqVest}/{codCurso}")
	public CandidatoDTO obterCandidato(@PathVariable Long cpf,@PathVariable Long codVest, @PathVariable Long seqVest, @PathVariable Long codCurso ) {		
		logger.info("INÍCIO InscricaoController.obterCandidato");
		return inscricaoService.obterCandidato(cpf, codVest, seqVest, codCurso);
	}	
	
	@ApiOperation(
			value = "Obtem candidato por numero de inscricao e codvest", 
			notes = "Obtem candidato por numero de inscricao e codvest", response = CandidatoDTO.class)
	@GetMapping("/candidato/{nrInscricao}/{codVest}")
	public CandidatoDTO obterCandidatoPorNrInscricaoECodVest(@PathVariable Long nrInscricao,@PathVariable Long codVest ) {		
		logger.info("INÍCIO InscricaoController.obterCandidatoPorNrInscricaoECodVest");
		return inscricaoService.obterCandidatoPorNrInscricaoECodVest(nrInscricao, codVest);
	}
	
	@ApiOperation(
			value = "Obtem candidato por idCanIns", 
			notes = "Obtem candidato por idCanIns", response = CandidatoDTO.class)
	@GetMapping("/candidato/{idCanIns}")
	public CandidatoDTO obterCandidatoPorIdCanIns(@PathVariable Long idCanIns) {		
		logger.info("INÍCIO InscricaoController.obterCandidatoPorIdCanIns");
		return inscricaoService.obterCandidatoPorIdCanIns(idCanIns);
	}
	
	@ApiOperation(
			value = "Obtem lista de documentos solicitados por empresa e tipo de ingresso", 
			notes = "Obtem lista de documentos solicitados por empresa e tipo de ingresso")
	@GetMapping("/documentos/{codEmpresa}/{tipoIngresso}")
	public List<DocsMatricDTO> obterDocumentosMatric(@PathVariable Long codEmpresa, @PathVariable Integer tipoIngresso) {
		logger.info("INÍCIO InscricaoController.obterDocumentosMatric");
		return inscricaoService.obterDocumentosMatric(codEmpresa, tipoIngresso);
	}
	
	@ApiOperation(
			value = "Consulta no CRM por cpf", 
			notes = "Consulta no CRM por cpf", 
			response = InscricaoDTO.class)
	@PostMapping("/obterInscricaoPorCPF")
	public ResponseEntity<InscricaoDTO> obterInscricaoPorCPF(@RequestBody String cpf) {
		logger.info("INÍCIO InscricaoController.obterInscricaoPorCPF");
		return inscricaoService.obterInscricaoPorCPF(cpf);
		
	}
	
	@ApiOperation(
			value = "Salvar Inscricao", 
			notes = "Salvar Inscricao", 
			response = InscricaoDTO.class)
	@PostMapping
	public ResponseEntity<InscricaoDTO> salvarInscricao(@RequestBody InscricaoDTO inscricao) {
		logger.info("INÍCIO InscricaoController.salvarInscricao");
		return inscricaoService.salvarInscricao(inscricao);
	}
	
	@ApiOperation(
			value = "Esqueci minha senha busca por cpf", 
			notes = "Esqueci minha senha busca por cpf")
	@PostMapping("/esqueciMinhaSenha")
	public ResponseEntity<InscricaoDTO> esqueciMinhaSenha(@RequestBody String cpf) {
		logger.info("INÍCIO InscricaoController.esqueciMinhaSenha");		
		return inscricaoService.esqueciMinhaSenha(cpf);
	}
	
	@ApiOperation(
			value = "obtem cadastro do polo pelo idpolo", 
			notes = "obtem cadastro do polo pelo idpolo")
	@GetMapping("/cadpol/{idPolo}")
	public ResponseEntity<CadpolDTO> getCadPol(@PathVariable Long idPolo) {
		logger.info("INÍCIO InscricaoController.getCadPol");		
		return inscricaoService.getCadPol(idPolo);
	}
	
	@ApiOperation(
			value = "obtem cursos pelo codvest", 
			notes = "obtem cursos pelo codvest")
	@GetMapping("/cadcur/{codVest}")
	public ResponseEntity<List<CursoComboDTO>> getCodVest(@PathVariable Long codVest) {
		logger.info("INÍCIO InscricaoController.getCodVest");		
		return inscricaoService.getCursosCombo(codVest);
	}
	
	@ApiOperation(
			value = "obtem tipos de inscricao pelo codigo do vestibular", 
			notes = "obtem tipos de inscricao pelo codigo do vestibular")
	@GetMapping("/etapa2/{codVest}")
	public ResponseEntity<List<Etapa2DTO>> getListEtapa2CodVest(@PathVariable Long codVest) {
		logger.info("INÍCIO InscricaoController.getListEtapa2CodVest");		
		return inscricaoService.getListEtapa2CodVest(codVest);
	}
	
	@ApiOperation(
			value = "obtem lista de empresa", 
			notes = "obtem lista de empresa")
	@GetMapping("/empresa")
	public ResponseEntity<List<EmpresaDTO>> getListEmpresa() {
		logger.info("INÍCIO InscricaoController.getListEmpresa");		
		return inscricaoService.getListEmpresa();
	}

	@ApiOperation(
			value = "obtem pselet pelo codigo do vestibular", 
			notes = "obtem pselet pelo codigo do vestibular")
	@GetMapping("/pselet/{codVest}")
	public PseletDTO getPseletCodVest(@PathVariable Long codVest) {
		logger.info("INÍCIO InscricaoController.getListEtapa2CodVest");		
		return inscricaoService.getPselet(codVest);
	}


}
