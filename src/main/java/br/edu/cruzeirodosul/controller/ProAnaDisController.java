package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import br.edu.cruzeirodosul.business.dto.EmentaDTO;
import br.edu.cruzeirodosul.business.dto.ProAnaDisDTO;
import br.edu.cruzeirodosul.business.dto.SemestreDTO;
import br.edu.cruzeirodosul.business.interfaces.IProAnaDis;
import br.edu.cruzeirodosul.business.interfaces.IProAnaDisCargaHoraria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "proAnaDis", tags = "Disciplina Processo Analise")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping("/analisecurri/disciplina")
public class ProAnaDisController {
	
	@Autowired
	IProAnaDis proAnaDis;
	
	@Autowired
	IProAnaDisCargaHoraria proAnaDisCargaHoraria;
	
	Logger logger = Logger.getLogger(ProAnaDisController.class);
	
	@ApiOperation(value = "Salva uma Disciplina do Processo de Análise", notes = "Salva uma Disciplina do Processo de Análise", response = ProAnaDisDTO.class, responseContainer = "OBJ")
	@PostMapping()
	public ResponseEntity<ProAnaDisDTO> salvaProAnaDis(@RequestBody ProAnaDisDTO proAnaDisDTO) {
		logger.info("Inicio salvaProAnaDis");
		return proAnaDis.salvaProAnaDis(proAnaDisDTO);
	}
	
	@ApiOperation(value = "Atualiza uma Disciplina do Processo de Análise", notes = "Atualiza uma Disciplina do Processo de Análise", response = ProAnaDisDTO.class, responseContainer = "OBJ")
	@PutMapping()
	public ResponseEntity<ProAnaDisDTO> atualizaProAnaDis(@RequestBody ProAnaDisDTO proAnaDisDTO) {
		logger.info("Inicio atualizaProAnaDis");
		return proAnaDis.atualizaDisciplina(proAnaDisDTO);
	}
	
	@ApiOperation(value = "Deleta uma Disciplina do Processo de Análise", notes = "Deleta uma Disciplina do Processo de Análise", response = ProAnaDisDTO.class, responseContainer = "OBJ")
	@DeleteMapping("/{id}")
	public void deletaProAnaDis(@PathVariable Integer id) {
		logger.info("Inicio deletaProAnaDis");
		proAnaDis.deletaProAnaDis(id);
	}
	
	@ApiOperation(value = "Busca uma Disciplina do Processo de Análise", notes = "Busca uma Disciplina do Processo de Análise", response = ProAnaDisDTO.class, responseContainer = "OBJ")
	@GetMapping("/{id}")
	public ProAnaDisDTO getProAnaDis(@PathVariable Integer id) {
		logger.info("Inicio getProAnaDis");
		return proAnaDis.getProAnaDis(id);
	}
	
	@ApiOperation(value = "Busca todos as Disciplinas Processo de Análise", notes = "Busca todos as Disciplinas do Processo de Análise", response = ProAnaDisDTO.class, responseContainer = "OBJ")
	@GetMapping()
	public List<ProAnaDisDTO> getAll(){
		logger.info("Inicio getAll");
		return proAnaDis.getAll();
	}
	
	@ApiOperation(value = "Busca todos as Disciplinas do Processo de Análise", notes = "Busca todos as Disciplinas do Processo de Análise", response = ProAnaDisDTO.class, responseContainer = "OBJ")
	@GetMapping("/processo/{idProAna}")
	public List<ProAnaDisDTO> getByIdProAna(@PathVariable Integer idProAna) {
		logger.info("Inicio getByIdProAna");
		return proAnaDis.recuperarGrade(idProAna);
	}
	
	@ApiOperation(value = "Busca todos as Disciplinas Processo de Análise", notes = "Busca todos as Disciplinas do Processo de Análise", response = ProAnaDisDTO.class, responseContainer = "OBJ")
	@GetMapping("/semestre/{idProAna}")
	public List<SemestreDTO> getByIdProAnaSemestral(@PathVariable Integer idProAna){
		logger.info("Inicio getByIdProAnaSemestral");
		return proAnaDis.getByIdProAnaSemestral(idProAna);
	}
	
	@ApiOperation(value = "Busca semestre ideal para quantidade de horas informado", notes = "Busca semestre ideal para quantidade de horas informado")
	@GetMapping("/semestre/ideal/{idProAna}/{qtdhoras}")
	public Integer getByIdProAnaSemestralIdeal(@PathVariable Integer idProAna, @PathVariable Integer qtdhoras){
		logger.info("Inicio getByIdProAnaSemestralIdeal");
		return proAnaDis.getByIdProAnaSemestralIdeal(idProAna,qtdhoras);
	}
	
	@ApiOperation(value = "Busca ementa para uma disciplina", notes = "Busca ementa para uma disciplina", response = String.class, responseContainer = "OBJ")
	@GetMapping("/ementa/{codEmpresa}/{codDisciplina}")
	public EmentaDTO getEmenta(@PathVariable Long codEmpresa, @PathVariable Long codDisciplina) {
		logger.info("Inicio getEmenta");
		return proAnaDis.obterEmenta(codEmpresa, codDisciplina);
	}
	
	@ApiOperation(value = "Busca ementa para uma disciplina por idProAnaDis", notes = "Busca ementa para uma disciplina  por idProAnaDis", response = String.class, responseContainer = "OBJ")
	@GetMapping("/ementa/{idProAnaDis}")
	public String getEmentaByProAnaDis(@PathVariable Integer idProAnaDis) {
		logger.info("Inicio getEmentaByProAnaDis");
		return proAnaDis.obterEmentaByProAnaDis(idProAnaDis);
	}
	
	@ApiOperation(value = "Busca a quantidade de horas dispensa para o processo de analise", notes = "Busca a quantidade de horas dispensa para o processo de analise", response = String.class, responseContainer = "OBJ")
	@GetMapping("/carga/horaria/dispensada/{idProAna}")
	public Integer getcargaHorariaDispensada(@PathVariable Integer idProAna) {
		logger.info("Inicio getEmentaByProAnaDis");
		return proAnaDisCargaHoraria.cargaHorariaDispensada(idProAna);
	}

}
