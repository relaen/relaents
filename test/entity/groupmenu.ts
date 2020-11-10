
import {Menu} from './menu'
import {Group} from './group'
import { Entity, Id, Column, ManyToOne, JoinColumn } from '../../core/decorator/decorator';
import { BaseEntity } from '../../core/entity';

@Entity("t_group_menu",'tunnel')
export class GroupMenu extends BaseEntity{
	@Id()
	@Column({
		name:'group_menu_id',
		type:'int',
		nullable:false
	})
	groupMenuId:number;

	@ManyToOne({entity:GroupMenu,lazyFetch:true})
	@JoinColumn({name:'menu_id',refName:'menu_id'})
	menu:Menu;

	@ManyToOne({entity:GroupMenu,lazyFetch:true})
	@JoinColumn({name:'group_id',refName:'group_id'})
	group:Group;

}