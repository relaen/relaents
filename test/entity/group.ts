import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {GroupAuthority} from './groupauthority'
import {GroupMember} from './groupmember'
import {GroupMenu} from './groupmenu'

@Entity("t_group",'tunnel')
export class Group extends BaseEntity{
	@Id()
	@Column({
		name:'group_id',
		type:'int',
		nullable:false
	})
	private groupId:number;

	@Column({
		name:'group_name',
		type:'string',
		nullable:true
	})
	private groupName:string;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	private remarks:string;

	@OneToMany({entity:'GroupAuthority',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'group',eager:false})
	private groupAuthoritys:Array<GroupAuthority>;

	@OneToMany({entity:'GroupMember',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'group',eager:false})
	private groupMembers:Array<GroupMember>;

	@OneToMany({entity:'GroupMenu',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'group',eager:false})
	private groupMenus:Array<GroupMenu>;

	public getGroupId():number{
		return this.groupId;
	}
	public setGroupId(value:number){
		this.groupId = value;
	}

	public getGroupName():string{
		return this.groupName;
	}
	public setGroupName(value:string){
		this.groupName = value;
	}

	public getRemarks():string{
		return this.remarks;
	}
	public setRemarks(value:string){
		this.remarks = value;
	}

	public getGroupAuthoritys():Array<GroupAuthority>{
		return this.groupAuthoritys;
	}
	public setGroupAuthoritys(value:Array<GroupAuthority>){
		this.groupAuthoritys = value;
	}

	public getGroupMembers():Array<GroupMember>{
		return this.groupMembers;
	}
	public setGroupMembers(value:Array<GroupMember>){
		this.groupMembers = value;
	}

	public getGroupMenus():Array<GroupMenu>{
		return this.groupMenus;
	}
	public setGroupMenus(value:Array<GroupMenu>){
		this.groupMenus = value;
	}

}