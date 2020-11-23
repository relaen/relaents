import { Entity, Id, Column, ManyToOne, JoinColumn, OneToMany } from '../../core/decorator/decorator'
import { BaseEntity } from '../../core/baseentity'
import { EFkConstraint } from '../../core/entitydefine'
import {RoadLevel} from './roadlevel'
import {Area} from './area'
import {RoadFuncRel} from './roadfuncrel'
import {Tunnel} from './tunnel'

@Entity("t_road",'tunnel')
export class Road extends BaseEntity{
	@Id()
	@Column({
		name:'road_id',
		type:'int',
		nullable:false
	})
	private roadId:number;

	@ManyToOne({entity:'RoadLevel',eager:false})
	@JoinColumn({name:'road_level_id',refName:'road_level_id'})
	private roadLevel:RoadLevel;

	@ManyToOne({entity:'Area',eager:false})
	@JoinColumn({name:'area_id',refName:'area_id'})
	private area:Area;

	@Column({
		name:'road_name',
		type:'string',
		nullable:true
	})
	private roadName:string;

	@Column({
		name:'road_no',
		type:'string',
		nullable:true
	})
	private roadNo:string;

	@Column({
		name:'remarks',
		type:'string',
		nullable:true
	})
	private remarks:string;

	@OneToMany({entity:'RoadFuncRel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'road',eager:false})
	private roadFuncRels:Array<RoadFuncRel>;

	@OneToMany({entity:'Tunnel',onDelete:EFkConstraint.SETNULL,onUpdate:EFkConstraint.CASCADE,mappedBy:'road',eager:false})
	private tunnels:Array<Tunnel>;

	public getRoadId():number{
		return this.roadId;
	}
	public setRoadId(value:number){
		this.roadId = value;
	}

	public getRoadLevel():RoadLevel{
		return this.roadLevel;
	}
	public setRoadLevel(value:RoadLevel){
		this.roadLevel = value;
	}

	public getArea():Area{
		return this.area;
	}
	public setArea(value:Area){
		this.area = value;
	}

	public getRoadName():string{
		return this.roadName;
	}
	public setRoadName(value:string){
		this.roadName = value;
	}

	public getRoadNo():string{
		return this.roadNo;
	}
	public setRoadNo(value:string){
		this.roadNo = value;
	}

	public getRemarks():string{
		return this.remarks;
	}
	public setRemarks(value:string){
		this.remarks = value;
	}

	public getRoadFuncRels():Array<RoadFuncRel>{
		return this.roadFuncRels;
	}
	public setRoadFuncRels(value:Array<RoadFuncRel>){
		this.roadFuncRels = value;
	}

	public getTunnels():Array<Tunnel>{
		return this.tunnels;
	}
	public setTunnels(value:Array<Tunnel>){
		this.tunnels = value;
	}

}