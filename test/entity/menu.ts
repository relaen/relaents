import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {GroupMenu} from './groupmenu'

@Entity("t_menu",'tunnel')
export class Menu extends BaseEntity{
	@Id()
	@Column({
		name:'menu_id',
		type:'int',
		nullable:false
	})
	private menuId:number;

	@ManyToOne({entity:'Menu',eager:false})
	@JoinColumn({name:'parent_id',refName:'menu_id'})
	private menu:Menu;

	@Column({
		name:'title',
		type:'string',
		nullable:true
	})
	private title:string;

	@Column({
		name:'url',
		type:'string',
		nullable:true
	})
	private url:string;

	@OneToMany({entity:'GroupMenu',onDelete:EFkConstraint.CASCADE,onUpdate:EFkConstraint.CASCADE,mappedBy:'menu',eager:false})
	private groupMenus:Array<GroupMenu>;

	@OneToMany({entity:'Menu',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'menu',eager:false})
	private menus:Array<Menu>;

	public getMenuId():number{
		return this.menuId;
	}
	public setMenuId(value:number){
		this.menuId = value;
	}

	public getMenu():Menu{
		return this.menu;
	}
	public setMenu(value:Menu){
		this.menu = value;
	}

	public getTitle():string{
		return this.title;
	}
	public setTitle(value:string){
		this.title = value;
	}

	public getUrl():string{
		return this.url;
	}
	public setUrl(value:string){
		this.url = value;
	}

	public getGroupMenus():Array<GroupMenu>{
		return this.groupMenus;
	}
	public setGroupMenus(value:Array<GroupMenu>){
		this.groupMenus = value;
	}

	public getMenus():Array<Menu>{
		return this.menus;
	}
	public setMenus(value:Array<Menu>){
		this.menus = value;
	}

}