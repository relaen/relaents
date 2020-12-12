import { Entity, BaseEntity, Id, Column, ManyToOne, JoinColumn, OneToMany, EFkConstraint, EntityProxy } from "../..";

@Entity("t_menu",'tunnel')
export class Menu extends BaseEntity{
	@Id()
	@Column({
		name:'menu_id',
		type:'int',
		nullable:false
	})
	private menuId:number;

	@ManyToOne({entity:'Menu'})
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

	@OneToMany({entity:'Menu',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'menu'})
	private menus:Array<Menu>;

	constructor(idValue?:number){
		super();
		this.menuId = idValue;
	}
	public getMenuId():number{
		return this.menuId;
	}
	public setMenuId(value:number){
		this.menuId = value;
	}

	public async getMenu():Promise<Menu>{
		return await EntityProxy.get(this,'menu');
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

	public async getMenus():Promise<Array<Menu>>{
		return await EntityProxy.get(this,'menus');
	}
	public setMenus(value:Array<Menu>){
		this.menus = value;
	}
}