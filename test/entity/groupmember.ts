import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {User} from './user'
import {Group} from './group'

@Entity("t_group_member",'tunnel')
export class GroupMember extends BaseEntity{
	@Id()
	@Column({
		name:'group_member_id',
		type:'int',
		nullable:false
	})
	private groupMemberId:number;

	@ManyToOne({entity:'User',eager:false})
	@JoinColumn({name:'user_id',refName:'user_id'})
	private user:User;

	@ManyToOne({entity:'Group',eager:false})
	@JoinColumn({name:'group_id',refName:'group_id'})
	private group:Group;

	public getGroupMemberId():number{
		return this.groupMemberId;
	}
	public setGroupMemberId(value:number){
		this.groupMemberId = value;
	}

	public getUser():User{
		return this.user;
	}
	public setUser(value:User){
		this.user = value;
	}

	public getGroup():Group{
		return this.group;
	}
	public setGroup(value:Group){
		this.group = value;
	}

}