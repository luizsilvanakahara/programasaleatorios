package br.edu.cruzeirodosul.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.multipart.MultipartFile;

import br.edu.cruzeirodosul.business.dto.AnexoDTO;
import br.edu.cruzeirodosul.business.dto.ProAnexoDTO;
import br.edu.cruzeirodosul.business.dto.SituacaoAnexoDTO;
import br.edu.cruzeirodosul.business.interfaces.IProAnexo;
import br.edu.cruzeirodosul.exception.CustomGenericException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestScope
@CrossOrigin
@Api(value = "proAnexo", tags = "Anexo Processo Analise")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Operação realizada com sucesso."),
		@ApiResponse(code = 204, message = "Operação realizada sem retorno."),
		@ApiResponse(code = 400, message = "Chamada inválida."),
		@ApiResponse(code = 401, message = "Não autorizado o acesso."),
		@ApiResponse(code = 403, message = "O recurso que você tentou acessar é proibido."),
		@ApiResponse(code = 404, message = "O recurso não foi encontrado.") })
@RequestMapping("/analisecurri/processo/anexo")
public class ProAnexoController {
	
	@Autowired
	IProAnexo proAnexo;
	
	Logger logger = Logger.getLogger(ProAnexoController.class);
	
	@ApiOperation(value = "Salva um Anexo do Processo de Análise", notes = "Salva um Anexo do Processo de Análise", response = ProAnexoDTO.class)
	@PostMapping()
	public ProAnexoDTO salvaProAnexo(@RequestBody ProAnexoDTO proAnexoDTO) {
		logger.info("Inicio salvaProAnexo");
		return proAnexo.salvaProAnexo(proAnexoDTO);
	}
	
	@ApiOperation(value = "Atualiza um Anexo do Processo de Análise", notes = "Atualiza um Anexo do Processo de Análise", response = ProAnexoDTO.class)
	@PutMapping()
	public ProAnexoDTO atualizaProAnexo(@RequestBody ProAnexoDTO proAnexoDTO) {
		logger.info("Inicio atualizaProAnexo");
		return proAnexo.salvaProAnexo(proAnexoDTO);
	}
	
	@ApiOperation(value = "Deleta um Anexo do Processo de Análise", notes = "Deleta um Anexo do Processo de Análise", response = ProAnexoDTO.class)
	@DeleteMapping("/{id}")
	public void deletaProAnexo(@PathVariable Integer id) {
		logger.info("Inicio deletaProAnexo");
		proAnexo.deletaProAnexo(id);
	}
	
	@ApiOperation(value = "Busca um Anexo do Processo de Análise", notes = "Busca um Anexo do Processo de Análise", response = ProAnexoDTO.class)
	@GetMapping("/{id}")
	public ProAnexoDTO getProAnexo(@PathVariable Long id) {
		logger.info("Inicio getProAnexo");
		return proAnexo.getProAnexo(id.intValue());
	}
	
	@ApiOperation(value = "Busca uma  lista de Anexos a partir do id do Processo de Análise", notes = "Busca uma  lista de Anexos a partir do id do Processo de Análise", response = ProAnexoDTO.class)
	@GetMapping("/proAna/{idProAna}")
	public List<ProAnexoDTO> getProAnexoByIdProAna(@PathVariable Long idProAna) {
		logger.info("Inicio getProAnexoByIdProAna");
		return proAnexo.getProAnexosByIdProAna(idProAna);
	}
	
	@ApiOperation(value = "Busca todos as Anexos Processo de Análise", notes = "Busca todos as Anexos do Processo de Análise", response = ProAnexoDTO.class)
	@GetMapping()
	public List<ProAnexoDTO> getAll(){
		logger.info("Inicio getAll");
		return proAnexo.getAll();
	}
	
	@ApiOperation(value = "Submete arquivo para a tabela de anexos retornando o idAnexo", notes = "Busca todos as Anexos do Processo de Análise", response = Long.class)
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public Long handleFileUpload(@RequestParam("file") MultipartFile file) {
		return proAnexo.store(file);
    }
	
	@ApiOperation(value = "Submete arquivo para a tabela de anexos retornando situacoAnexoDTO", notes = "Busca todos as Anexos do Processo de Análise", response = SituacaoAnexoDTO.class)
	@PostMapping("/upload/complete")
    public AnexoDTO handleFileUploadAdvanced(@RequestParam("file") MultipartFile file) {
		return proAnexo.storeAdvanced(file);
    }
	
	@ApiOperation(value = "recupera um objeto do tipo anexoDTO da tabela de anexos por idAnexo", notes = "Busca todos as Anexos do Processo de Análise", response = AnexoDTO.class)
	@GetMapping("/obter/{id}")
    public AnexoDTO obterAnexo(@PathVariable Long id) {
		return proAnexo.obterAnexo(id);
    }

	@ApiOperation(value = "Exclui arquivo da tabela de anexos", notes = "Busca todos as Anexos do Processo de Análise")
	@DeleteMapping("/file/{id}")
    public void deleteAnexo(@PathVariable Long id) {
		proAnexo.excluirAnexo(id);
    }

	
	@ApiOperation(value = "recupera um arquivo para download da tabela de anexos por idAnexo", notes = "Busca todos as Anexos do Processo de Análise", response = AnexoDTO.class)
	@GetMapping("/download/{id}")
    public void downloadAnexo(@PathVariable Long id, final HttpServletRequest request, final HttpServletResponse response) {
		AnexoDTO anexo = this.obterAnexo(id);
		byte[] decoded = org.apache.commons.codec.binary.Base64.decodeBase64(anexo.getAnexo().getBytes());

		response.reset();
		response.setContentType(selecionaTipoContentType(anexo.getNomeAnexo()));
		response.setHeader("Content-Disposition", "inline; filename='" + anexo.getNomeAnexo() + "'");
		response.setContentLength(decoded.length);		

		try {
			InputStream input = new ByteArrayInputStream(decoded);
			OutputStream output = response.getOutputStream();
			IOUtils.copyLarge(input, output);
            output.flush();
		} catch (IOException e) {
			throw new CustomGenericException("500", "anexo download", e, e.getStackTrace()[0]);
		}
		
    }
	
	public String selecionaTipoContentType(String filename) {
		String retorno = "application/octet-stream";
		String extensao = filename.substring(filename.indexOf('.')).toLowerCase(); 
		if (".pdf.".equals(extensao)) {
			retorno = "application/pdf";
		} else if (".jpg".equals(extensao) || ".jpeg".equals(extensao)) {
			retorno = "image/jpeg";
		} else if (".png".equals(extensao)) {
			retorno = "image/png";
		}
		return retorno;
	}
	
}
