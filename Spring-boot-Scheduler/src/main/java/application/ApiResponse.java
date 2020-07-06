package application;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "api_response")
public class ApiResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "response_id")
	private Integer responseId;

	private String response;

	@Column(name = "api_execution_date")
	@CreationTimestamp
	private LocalDateTime apiExecutionDate;

}
