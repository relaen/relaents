import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/entity'
import { EFkConstraint } from '../../core/entitydefine'
import {Menu} from './menu'
import {Group} from './group'

@Entity("t_group_menu",'tunnel')
export class GroupMenu extends BaseEntity{
	@Id()
	@Column({
		name:'group_menu_id',
		type:'int',
		nullable:false
	})
	private groupMenuId:number;

	@ManyToOne({entity:'Menu',eager:false})
	@JoinColumn({name:'menu_id',refName:'menu_id'})
	private menu:Menu;

	@ManyToOne({entity:'Group',eager:false})
	@JoinColumn({name:'group_id',refName:'group_id'})
	private group:Group;

	public getGroupMenuId():number{
		return this.groupMenuId;
	}
	public setGroupMenuId(value:number){
		this.groupMenuId = value;
	}

	public getMenu():Menu{
		return this.menu;
	}
	public setMenu(value:Menu){
		this.menu = value;
	}

	public getGroup():Group{
		return this.group;
	}
	public setGroup(value:Group){
		this.group = value;
	}

}