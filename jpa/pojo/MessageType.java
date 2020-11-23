package dao.pojo;

import javax.persistence.TableGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
// default package
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * MessageType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_message_type", catalog = "tiandian")
public class MessageType extends BasePojo implements java.io.Serializable {
	// Fields
	private Long message_type_id;
	private String message_type_name;
	private Set<Message> messages = new HashSet<Message>(0);

	// Constructors
	/** default constructor */
	public MessageType() {
	}

	/** minimal constructor */
	public MessageType(Long message_type_id) {
		this.message_type_id = message_type_id;
	}

	/** full constructor */
	public MessageType(Long message_type_id, String message_type_name, Set<Message> messages) {
		this.message_type_id = message_type_id;
		this.message_type_name = message_type_name;
		this.messages = messages;
	}

	// Property accessors
	@Id
	@TableGenerator(name = "MESSAGETYPE_GEN", table = "sequence", pkColumnName = "SEQ_NAME", valueColumnName = "SEQ_COUNT", pkColumnValue = "SEQ_MESSAGETYPE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MESSAGETYPE_GEN")
	@Column(name = "message_type_id", unique = true, nullable = false)
	public Long getMessage_type_id() {
		return this.message_type_id;
	}

	public void setMessage_type_id(Long message_type_id) {
		this.message_type_id = message_type_id;
	}

	@Column(name = "message_type_name", length = 50)
	public String getMessage_type_name() {
		return this.message_type_name;
	}

	public void setMessage_type_name(String message_type_name) {
		this.message_type_name = message_type_name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "messageType")
	public Set<Message> getMessages() {
		return this.messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	@Override
	public Object getEntityId() {
		return this.message_type_id;
	}
}