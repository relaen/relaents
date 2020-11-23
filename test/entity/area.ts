import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {Agent} from './agent'
import {Provider} from './provider'
import {Road} from './road'

@Entity("t_area",'tunnel')
export class Area extends BaseEntity{
	@Id()
	@Column({
		name:'area_id',
		type:'int',
		nullable:false
	})
	private areaId:number;

	@ManyToOne({entity:'Area',eager:false})
	@JoinColumn({name:'parent_id',refName:'area_id'})
	private area:Area;

	@Column({
		name:'area_name',
		type:'string',
		nullable:true
	})
	private areaName:string;

	@Column({
		name:'area_code',
		type:'string',
		nullable:true
	})
	private areaCode:string;

	@Column({
		name:'zip_code',
		type:'string',
		nullable:true
	})
	private zipCode:string;

	@Column({
		name:'depth',
		type:'int',
		nullable:true
	})
	private depth:number;

	@OneToMany({entity:'Agent',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'area',eager:false})
	private agents:Array<Agent>;

	@OneToMany({entity:'Area',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'area',eager:false})
	private areas:Array<Area>;

	@OneToMany({entity:'Provider',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'area',eager:false})
	private providers:Array<Provider>;

	@OneToMany({entity:'Road',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'area',eager:false})
	private roads:Array<Road>;

	public getAreaId():number{
		return this.areaId;
	}
	public setAreaId(value:number){
		this.areaId = value;
	}

	public getArea():Area{
		return this.area;
	}
	public setArea(value:Area){
		this.area = value;
	}

	public getAreaName():string{
		return this.areaName;
	}
	public setAreaName(value:string){
		this.areaName = value;
	}

	public getAreaCode():string{
		return this.areaCode;
	}
	public setAreaCode(value:string){
		this.areaCode = value;
	}

	public getZipCode():string{
		return this.zipCode;
	}
	public setZipCode(value:string){
		this.zipCode = value;
	}

	public getDepth():number{
		return this.depth;
	}
	public setDepth(value:number){
		this.depth = value;
	}

	public getAgents():Array<Agent>{
		return this.agents;
	}
	public setAgents(value:Array<Agent>){
		this.agents = value;
	}

	public getAreas():Array<Area>{
		return this.areas;
	}
	public setAreas(value:Array<Area>){
		this.areas = value;
	}

	public getProviders():Array<Provider>{
		return this.providers;
	}
	public setProviders(value:Array<Provider>){
		this.providers = value;
	}

	public getRoads():Array<Road>{
		return this.roads;
	}
	public setRoads(value:Array<Road>){
		this.roads = value;
	}

}