package br.edu.cruzeirodosul.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import br.edu.cruzeirodosul.business.dto.GGradeDTO;
import br.edu.cruzeirodosul.business.dto.GradeDTO;
import br.edu.cruzeirodosul.business.interfaces.IGrade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "grade", tags = "GRADE")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping("/grade")
public class GradeController {
	
	@Autowired
	IGrade grade;
	
	private static final Logger logger = Logger.getLogger(GradeController.class);
	
	@ApiOperation(value = "Busca a grade de disciplinas por código Empresa, código Curso, Ano, Semestre e Série", notes = "Busca a grade de disciplinas por código Empresa, código Curso, Ano, Semestre e Série", response = GradeDTO.class)
	@GetMapping("/empresa/curso/{idEmpresa}/{idCurso}/{anoLetivo}/{semLetivo}/{serie}")
	public List<GradeDTO> getGradeByEmpresaCursoAnoSemSerie(@PathVariable Integer idEmpresa,@PathVariable Integer idCurso, @PathVariable Integer anoLetivo, @PathVariable Integer semLetivo,@PathVariable  Integer serie) {
		logger.info("Início getGradeByEmpresaCursoAnoSemSerie");
		return grade.getGradeByEmpresaCursoAnoSemSerie(idEmpresa, idCurso, anoLetivo, semLetivo, serie);
	}
	
	@ApiOperation(value = "Busca a grade de disciplinas por código Empresa, código Curso", notes = "Busca a grade de disciplinas por código Empresa, código Curso", response = GGradeDTO.class)
	@GetMapping("/empresa/curso/{idEmpresa}/{idCurso}")
	public List<GGradeDTO> getGradeByEmpresaCurso(@PathVariable Integer idEmpresa,@PathVariable Integer idCurso) {
		logger.info("Início getGradeByEmpresaCurso");
		return grade.getGradeByEmpresaCurso(idEmpresa, idCurso);
	}
	
	@ApiOperation(value = "Busca a grade de disciplinas por código Grade", notes = "Busca a grade de disciplinas por código Grade", response = GradeDTO.class)
	@GetMapping("{idGrade}")
	public List<GradeDTO> getGradeByIdGrade(@PathVariable Integer idGrade) {
		logger.info("Início getGradeByIdgrade");
		return grade.getGradeByIdgrade(idGrade);
	}

}
