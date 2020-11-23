import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {Authority} from './authority'
import {Group} from './group'

@Entity("t_group_authority",'tunnel')
export class GroupAuthority extends BaseEntity{
	@Id()
	@Column({
		name:'group_authority_id',
		type:'int',
		nullable:false
	})
	private groupAuthorityId:number;

	@ManyToOne({entity:'Authority',eager:false})
	@JoinColumn({name:'authority',refName:'authority'})
	private authority:Authority;

	@ManyToOne({entity:'Group',eager:false})
	@JoinColumn({name:'group_id',refName:'group_id'})
	private group:Group;

	public getGroupAuthorityId():number{
		return this.groupAuthorityId;
	}
	public setGroupAuthorityId(value:number){
		this.groupAuthorityId = value;
	}

	public getAuthority():Authority{
		return this.authority;
	}
	public setAuthority(value:Authority){
		this.authority = value;
	}

	public getGroup():Group{
		return this.group;
	}
	public setGroup(value:Group){
		this.group = value;
	}

}