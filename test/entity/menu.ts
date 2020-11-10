
import {GroupMenu} from './groupmenu'
import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';
import { EFkConstraint } from '../../core/entitydefine';

@Entity("t_menu",'tunnel')
export class Menu extends BaseEntity{
	@Id()
	@Column({
		name:'menu_id',
		type:'int',
		nullable:false
	})
	menuId:number;

	@ManyToOne({entity:Menu,lazyFetch:true})
	@JoinColumn({name:'parent_id',refName:'parent_id'})
	menu:Menu;

	@Column({
		name:'title',
		type:'string',
		nullable:true
	})
	title:string;

	@Column({
		name:'url',
		type:'string',
		nullable:true
	})
	url:string;

	@OneToMany({entity:'GroupMenu',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'menu',lazyFetch:true})
	groupMenus:Array<GroupMenu>;

	@OneToMany({entity:'Menu',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'menu',lazyFetch:true})
	menus:Array<Menu>;

}